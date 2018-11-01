package org.ecclesia.directory;

import org.ecclesia.commons.spring.ApplicationUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring application entry point.
 */
@SpringBootApplication
public class Application {

  /**
   * Application main function.
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    ApplicationUtils.applicationMain(args, Application.class);
  }
}
