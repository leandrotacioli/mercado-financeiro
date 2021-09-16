package com.leandrotacioli.mercadofinanceiro;

import com.leandrotacioli.mercadofinanceiro.cotacoes.Cotacao;
import com.leandrotacioli.mercadofinanceiro.cotacoes.MFinance;
import com.leandrotacioli.mercadofinanceiro.cotacoes.YahooFinance;

import java.util.ArrayList;
import java.util.List;

public class MercadoFinanceiro {

    public static void main(String[] args) {
        List<String> tickers = new ArrayList<>();
        tickers.add("PETR3"); // Petrobras ON
        tickers.add("PETR4"); // Petrobras PN
        tickers.add("BBAS3"); // Banco do Brasil
        tickers.add("MGLU3"); // Magazine Luiza
        tickers.add("AMER3"); // Americanas
        tickers.add("XXXX4"); // Empresa inexistente

        for (String ticker : tickers) {
            Cotacao cotacaoYahooFinance = new YahooFinance(ticker);
            cotacaoYahooFinance.getCotacaoAtual();

            Cotacao cotacaoMFinance = new MFinance(ticker);
            cotacaoMFinance.getCotacaoAtual();

            System.out.println("--------------------------------------------------------------------------------------------");
        }
    }

}