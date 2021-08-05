package com.leandrotacioli.mercadofinanceiro.models;

import lombok.Data;

@Data
public class Cotacao {

    private String ticker;
    private String empresa;
    private double valorAtual;
    private double valorFechamentoAnterior;

    public Cotacao(String ticker) {
        this.ticker = ticker;
    }

}