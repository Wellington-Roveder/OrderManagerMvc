package com.ordermanagermvc.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class Logger {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Logger(){}

    private static class Holder{
        private static final  Logger INSTANCE = new Logger();
    }

    public static  Logger getInstance(){
        return  Holder.INSTANCE;
    }

    public enum LoggerType{ ERROR, WARNING, INFO}

    private void log(String mensagem, LoggerType loggerType, String erro){
        String timeStamp = LocalDateTime.now().format(formatter);
        System.out.println("Mensagem: " + mensagem + "\nTipo: " + loggerType + "\nErro: " + erro + "\nData: " + timeStamp);
    }

    public void info(String mensagem){
        log(mensagem, LoggerType.INFO, null);
    }

    public  void error(String mensagem, Throwable erro){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        erro.printStackTrace(pw);
        String stackTraceStr = sw.toString();
        String mensagemErro = mensagem + "\n" + stackTraceStr;

        log(mensagem, LoggerType.ERROR, mensagemErro);
    }
    public void warning(String mensagem){
        log(mensagem, LoggerType.WARNING, null);
    }


}
