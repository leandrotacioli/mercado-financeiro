package com.leandrotacioli.mercadofinanceiro.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    private HttpClient() {

    }

    /**
     * Realiza uma requisição HTTP através do método <i>GET</i>.
     *
     * @param url
     *
     * @return jsonObject
     *
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject makeHttpGetRequest(final String url) throws UnsupportedEncodingException, IOException, JSONException {
        JSONObject jsonObject = null;

        try {
            URL obj = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) obj.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

            int intResponseCode = urlConnection.getResponseCode();

            if (intResponseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuilder response = new StringBuilder();
                String responseLine = null;

                while ((responseLine = bufferedReader.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                jsonObject = new JSONObject(response.toString());

                bufferedReader.close();
            }

            urlConnection.disconnect();

        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException();

        } catch (IOException e) {
            throw new IOException(e);

        } catch (JSONException e) {
            throw new JSONException(e);
        }

        return jsonObject;
    }

}
