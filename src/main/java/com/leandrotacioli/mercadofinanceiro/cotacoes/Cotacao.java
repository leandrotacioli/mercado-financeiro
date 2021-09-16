package com.leandrotacioli.mercadofinanceiro.cotacoes;

import lombok.Data;

@Data
public abstract class Cotacao {

    private String ticker;
    private String empresa;
    private double valorAtual;
    private double valorFechamentoAnterior;

    public Cotacao(String ticker) {
        this.ticker = ticker;
    }

    public abstract void getCotacaoAtual();

    public double getValorVariacao() {
        return ((getValorAtual() - getValorFechamentoAnterior()) / getValorFechamentoAnterior()) * 100;
    }

}