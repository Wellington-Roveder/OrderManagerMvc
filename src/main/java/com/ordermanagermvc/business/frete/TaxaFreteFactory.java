package com.ordermanagermvc.business.frete;

public class TaxaFreteFactory {
    public static TaxaFrete criarTaxaFrete(TiposFrete tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de Frete não pode ser nulo.");
        }
            return switch (tipo) {
                case SEDEX -> new FreteSedex();
                case PAC -> new FretePac();
                case RETIRADA_LOCAL -> new FreteRetiradaLocal();
                default -> throw new IllegalArgumentException("Tipo de frete não suportado: " + tipo);
            };
        }
    }

