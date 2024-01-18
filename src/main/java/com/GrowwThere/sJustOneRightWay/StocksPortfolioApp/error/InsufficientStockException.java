package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error;

public class InsufficientStockException extends Exception{

  public InsufficientStockException() {
    super();
  }

  public InsufficientStockException(String message) {
    super(message);
  }

  public InsufficientStockException(String message, Throwable cause) {
    super(message, cause);
  }

  public InsufficientStockException(Throwable cause) {
    super(cause);
  }

  protected InsufficientStockException(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
