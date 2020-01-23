package com.demo.camelproblem2;

import org.apache.camel.main.Main;

public class App {
  public static void main(String... args) throws Exception {
    Main main = new Main();
    main.addRoutesBuilder(new MainRoute());
    main.run(args);
  }
}