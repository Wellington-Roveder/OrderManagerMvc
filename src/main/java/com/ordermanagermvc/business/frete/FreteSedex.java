package com.ordermanagermvc.business.frete;

import java.math.BigDecimal;

public class FreteSedex implements TaxaFrete {

    private static final BigDecimal TAXA_FIXA_SEDEX = new BigDecimal("15.50");
    private static final BigDecimal ADICIONAL_POR_KG = new BigDecimal("4.20");
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Override
    public void calcularTaxa(BigDecimal peso) {
        if (peso == null || peso.compareTo(BigDecimal.ZERO) <= 0) {
            this.valorTotal = TAXA_FIXA_SEDEX;
            return;
        }

        BigDecimal valorAdicional = peso.multiply(ADICIONAL_POR_KG);
        this.valorTotal = TAXA_FIXA_SEDEX.add(valorAdicional);

    }


    public BigDecimal getValorTotal() {
        return this.valorTotal;
    }
}
