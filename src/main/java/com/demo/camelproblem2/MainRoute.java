package com.demo.camelproblem2;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class MainRoute extends RouteBuilder {

  private ExecutorService executorService = Executors.newFixedThreadPool(20);

  public void configure() {
    from("file:src/main/resources/?fileName=data_4129.csv&noop=true&idempotent=false&delay=2000")
      .bean(MainProcessor.class, "resetCounter")
      .split(body().tokenize("\n")).streaming().executorService(executorService)
        .to("dataformat:bindy-csv:unmarshal?classType=com.demo.camelproblem2.Employee")
        .to("direct:agg")
      .end()
    ;

    from("direct:agg")
      .onCompletion().onWhen(header(Exchange.SPLIT_COMPLETE))
        .process(e -> {
          int css = Integer.parseInt(e.getProperty(Exchange.SPLIT_SIZE).toString());
          MainProcessor mainProcessor = new MainProcessor();
          int c = mainProcessor.getCounter();
          log.info("CSS: {}, counter: {}", css, c);
          if (css != c) {
            log.error("RECONCILIATION PROBLEM");
          }
        })
      .end()

      .aggregate(constant(true), new ArrayListAggregationStrategy())
        .completionSize(1000)
        .completionPredicate(header(Exchange.SPLIT_COMPLETE))
        .eagerCheckCompletion()

        .bean(MainProcessor.class, "incrementCounter")
        .process(e -> {
          log.info("Batch size: {}, subtotal: {}", ((ArrayList) e.getIn().getBody()).size(), e.getIn().getHeader("counter"));
        })
      .end()
    ;
  }
}
