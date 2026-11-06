package com.ordermanagermvc.business.frete;

import java.math.BigDecimal;

public class FreteRetiradaLocal implements TaxaFrete {
    @Override
    public void calcularTaxa(BigDecimal taxa) {
        taxa = BigDecimal.ZERO;
    }
}
