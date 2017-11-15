package com.inceptai.neo.es;

/**
 * General logger class/interface to use. This class cannot be directly instantiated, use the
 * factory constructor instead.
 */
public class Logger {
  private String tag;


  public static Logger get(Class classObject) {
    return new ConsoleLogger(classObject.getSimpleName());
  }

  private Logger(String className) {
    tag = className;
  }

  public void info(String message) {

  }

  public void i(String message) {
    info(message);
  }

  public void w(String message) {
    warning(message);
  }

  public void warning(String message) {

  }

  public void error(String message) {

  }

  public void e(String message) {
    error(message);
  }

  public void verbose(String message) {

  }

  public void v(String message) {
    verbose(message);
  }

  protected String getTag() {
    return tag;
  }

  /**
   * Logs to stdout.
   */
  public static class ConsoleLogger extends Logger {

    ConsoleLogger(String className) {
      super(className);
    }

    @Override
    public void info(String message) {
      System.out.println(getTag() + ":" + message);
    }

    @Override
    public void warning(String message) {
      System.out.println(getTag() + ":" + message);
    }

    @Override
    public void error(String message) {
      System.out.println(getTag() + ":" + message);
    }

    @Override
    public void verbose(String message) {
      System.out.println(getTag() + ":" + message);
    }
  }

}
