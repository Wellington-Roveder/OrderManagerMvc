package com.ordermanagermvc.business.frete;

import java.math.BigDecimal;

public class FretePac implements TaxaFrete {
    private static final BigDecimal TAXA_FIXA_PAC = new BigDecimal("18.00");
    private static final BigDecimal ADICIONAL_POR_KG = new BigDecimal("4.20");
    private BigDecimal valorTotal = BigDecimal.ZERO;


    @Override
    public void calcularTaxa(BigDecimal peso) {
        if (peso == null || peso.compareTo(BigDecimal.ZERO) <= 0) {
            this.valorTotal = TAXA_FIXA_PAC;
            return;
        }

        BigDecimal valorAdicional = peso.multiply(ADICIONAL_POR_KG);
        this.valorTotal = TAXA_FIXA_PAC.add(valorAdicional);

    }

    public BigDecimal getValorTotal() {
        return this.valorTotal;
    }
}
