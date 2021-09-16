package com.leandrotacioli.mercadofinanceiro.cotacoes;

import com.leandrotacioli.mercadofinanceiro.utils.DoubleTransformation;
import com.leandrotacioli.mercadofinanceiro.utils.HttpClient;
import org.json.JSONObject;

public class MFinance extends Cotacao {

    public MFinance(String ticker) {
        super(ticker);
    }

    @Override
    public void getCotacaoAtual() {
        try {
            JSONObject jsonObject = HttpClient.makeHttpGetRequest("https://mfinance.com.br/api/v1/stocks/" + getTicker());

            if (!jsonObject.getString("symbol").equals("")) {
                setTicker(jsonObject.getString("symbol"));
                setEmpresa(jsonObject.getString("name"));
                setValorAtual(jsonObject.getDouble("lastPrice"));
                setValorFechamentoAnterior(jsonObject.getDouble("closingPrice"));

                System.out.println(getTicker() + " - MFinance" +
                                   " | Cotação Atual: " + DoubleTransformation.doubleToString(getValorAtual(), 2) +
                                   " | Cotação Fechamento Anterior: " + DoubleTransformation.doubleToString(getValorFechamentoAnterior(), 2) +
                                   " | Variação: " + DoubleTransformation.doubleToString(getValorVariacao(), 2) + "%");

            } else {
                System.out.println(getTicker() + " - Cotação não encontrada na MFinance");
            }

        } catch (Exception e) {
            System.out.println(getTicker() + " - Erro ao retornar cotação na MFinance");
        }
    }

}