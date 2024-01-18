package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error;

public class StockNotFoundException extends Exception{
    public StockNotFoundException() {
        super();
    }

    public StockNotFoundException(String message) {
        super(message);
    }

    public StockNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockNotFoundException(Throwable cause) {
        super(cause);
    }

    protected StockNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
