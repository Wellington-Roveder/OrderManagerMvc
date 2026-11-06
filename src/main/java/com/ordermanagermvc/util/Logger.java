package com.ordermanagermvc.util;

import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class Logger {
    private static final String DIRETORIO_LOGS = "logs";
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
       try{
            String nomeArquivo = String.format("%s/%s.log", DIRETORIO_LOGS, LocalDate.now());
            String timeStamp = LocalDateTime.now().format(formatter);
            StringBuilder logBuilder = new StringBuilder();
            logBuilder.append(String.format("[%s] [%s] %s%n", timeStamp, loggerType, mensagem));


            if(loggerType == LoggerType.ERROR) {
                logBuilder.append(String.format("%s%n", erro));
            }

           exportarLog(nomeArquivo, logBuilder.toString());
        } catch (IOException e){
           System.err.println("[CRITICAL] Falha catastrófica ao gravar log no disco: " + e.getMessage());
           e.printStackTrace();
       }


    }

    public void info(String mensagem){
        log(mensagem, LoggerType.INFO, null);
    }

    public  void error(String mensagem, Throwable erro){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        erro.printStackTrace(pw);
        String stackTraceStr = sw.toString();
        log(mensagem, LoggerType.ERROR, stackTraceStr);
    }
    public void warning(String mensagem){
        log(mensagem, LoggerType.WARNING, null);
    }


    private void exportarLog(String caminhoArquivo, String log) throws IOException {
        Path path = Paths.get(caminhoArquivo);

        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            writer.write(log);

        }
    }
}
