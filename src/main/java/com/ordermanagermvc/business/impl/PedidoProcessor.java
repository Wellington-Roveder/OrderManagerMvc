package com.ordermanagermvc.business.impl;

public class PedidoProcessor implements Runnable{
    private final String lotePedido;

    public PedidoProcessor(String lotePedido) {
        this.lotePedido = lotePedido;
    }

    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName() + "\n Simulador pegou o " + lotePedido);
            Thread.sleep(4000);
            System.out.println(Thread.currentThread().getName() + lotePedido + " processado e finalizado!");
        } catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName() + "\nprocessamento do " + lotePedido + " foi interrompido.");
            Thread.currentThread().interrupt();
        }
    }
}
