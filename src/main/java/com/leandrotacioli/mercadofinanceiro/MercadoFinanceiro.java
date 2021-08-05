package com.leandrotacioli.mercadofinanceiro;

import com.leandrotacioli.mercadofinanceiro.models.Cotacao;
import com.leandrotacioli.mercadofinanceiro.utils.DoubleTransformation;
import com.leandrotacioli.mercadofinanceiro.utils.HttpClient;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MercadoFinanceiro {

    public static void main(String[] args) {
        List<String> tickers = new ArrayList<String>();
        tickers.add("PETR3"); // Petrobras ON
        tickers.add("PETR4"); // Petrobras PN
        tickers.add("BBAS3"); // Banco do Brasil
        tickers.add("MGLU3"); // Magazine Luiza
        tickers.add("XXXX4"); // Empresa inexistente

        for (String ticker : tickers) {
            try {
                Cotacao cotacao = getCotacao(ticker);

                if (cotacao.getTicker().equals(ticker)) {
                    double variacaoPreco = ((cotacao.getValorAtual() - cotacao.getValorFechamentoAnterior()) / cotacao.getValorFechamentoAnterior()) * 100;

                    System.out.println(cotacao.getTicker() +
                                       " - Cotação Atual: " + DoubleTransformation.doubleToString(cotacao.getValorAtual(), 2) +
                                       " | Cotação Fechamento Anterior: " + DoubleTransformation.doubleToString(cotacao.getValorFechamentoAnterior(), 2) +
                                       " | Variação: " + DoubleTransformation.doubleToString(variacaoPreco, 2) + "%");
                } else {
                    System.out.println(ticker + " - Cotação não encontrada");
                }

            } catch (Exception e) {
                System.out.println(ticker + " - Erro ao retornar dados");
            }
        }
    }

    public static Cotacao getCotacao(String ticker) {
        Cotacao cotacao = new Cotacao(ticker);

        try {
            JSONObject jsonObject = HttpClient.makeHttpGetRequest("https://mfinance.com.br/api/v1/stocks/" + cotacao.getTicker());

            cotacao.setTicker(jsonObject.getString("symbol"));
            cotacao.setEmpresa(jsonObject.getString("name"));
            cotacao.setValorAtual(jsonObject.getDouble("lastPrice"));
            cotacao.setValorFechamentoAnterior(jsonObject.getDouble("closingPrice"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cotacao;
    }

}