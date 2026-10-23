package com.ordermanagermvc.business.impl;

public class PedidoProcessor implements Runnable{
    private final String lotePedido;

    public PedidoProcessor(String lotePedido) {
        this.lotePedido = lotePedido;
    }

    @Override
    public void run() {
        try{
            System.out.println("\n[THREAD - INÍCIO] Simulador pegou o " + lotePedido);
            Thread.sleep(4000);
            System.out.println("\n[THREAD - SUCESSO] " + lotePedido + " processado e finalizado!");
        } catch (InterruptedException e){
            System.out.println("\n[THREAD - ERRO] O processamento do " + lotePedido + " foi interrompido.");
            Thread.currentThread().interrupt();
        }
    }
}
