package com.demo.camelproblem2;

import org.apache.camel.Exchange;

public class MainProcessor {

  private static int counter = 0;

  public void resetCounter(Exchange exchange) {
    counter = 0;
    setCounterHeader(exchange);
  }

  public void incrementCounter(Exchange exchange) {
    int agg = (int) exchange.getProperty(Exchange.AGGREGATED_SIZE);
    counter += agg;
    setCounterHeader(exchange);
  }

  public int getCounter() {
    return counter;
  }

  private void setCounterHeader(Exchange exchange) {
    exchange.getIn().setHeader("counter", counter);
  }
}
