package com.ordermanagermvc.business.frete;

import java.math.BigDecimal;

public interface TaxaFrete {
    void calcularTaxa(BigDecimal taxa);
}
