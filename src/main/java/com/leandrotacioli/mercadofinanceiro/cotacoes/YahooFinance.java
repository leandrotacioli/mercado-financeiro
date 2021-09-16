package com.leandrotacioli.mercadofinanceiro.cotacoes;

import com.leandrotacioli.mercadofinanceiro.utils.DoubleTransformation;
import com.leandrotacioli.mercadofinanceiro.utils.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class YahooFinance extends Cotacao {

    public YahooFinance(String ticker) {
        super(ticker);
    }

    @Override
    public void getCotacaoAtual() {
        try {
            JSONObject jsonObject = HttpClient.makeHttpGetRequest("https://query2.finance.yahoo.com/v7/finance/quote?symbols=" + getTicker() + ".SA");

            JSONObject quoteResponse = (JSONObject) jsonObject.get("quoteResponse");

            JSONArray jsonArrayResult = quoteResponse.getJSONArray("result");

            if (jsonArrayResult.length() > 0) {
                for (int indexArray = 0; indexArray < jsonArrayResult.length(); indexArray++) {
                    JSONObject jsonObjectResult = jsonArrayResult.getJSONObject(indexArray);

                    setTicker(jsonObjectResult.getString("symbol").split("\\.")[0]);
                    setEmpresa(jsonObjectResult.getString("longName"));
                    setValorAtual(jsonObjectResult.getDouble("regularMarketPrice"));
                    setValorFechamentoAnterior(jsonObjectResult.getDouble("regularMarketPreviousClose"));
                }

                System.out.println(getTicker() + " - Yahoo Finance" +
                        " | Cotação Atual: " + DoubleTransformation.doubleToString(getValorAtual(), 2) +
                        " | Cotação Fechamento Anterior: " + DoubleTransformation.doubleToString(getValorFechamentoAnterior(), 2) +
                        " | Variação: " + DoubleTransformation.doubleToString(getValorVariacao(), 2) + "%");

            } else {
                System.out.println(getTicker() + " - Cotação não encontrada na Yahoo Finance");
            }

        } catch (Exception e) {
            System.out.println(getTicker() + " - Erro ao retornar cotação na Yahoo Finance");
        }
    }

}