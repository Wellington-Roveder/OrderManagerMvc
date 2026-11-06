package com.ordermanagermvc.util;

public final class Logger {
    private Logger(){}

    private static class Holder{
        private static final  Logger INSTANCE = new Logger();
    }

    public static  Logger getInstance(){
        return  Holder.INSTANCE;
    }

    public enum LoggerType{ ERROR, WARNING, INFO}

    private void log(String mensagem, LoggerType loggerType, Throwable erro){

    }

    public void info(String mensagem){

    }

    public  void error(String mensagem, Throwable erro){

    }
    public void warning(String mensagem){

    }


}
