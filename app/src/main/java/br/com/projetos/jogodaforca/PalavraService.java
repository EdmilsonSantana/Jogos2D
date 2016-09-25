package br.com.projetos.jogodaforca;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Edmilson Santana on 25/09/2016.
 */
public class PalavraService {

    OkHttpClient client = new OkHttpClient();
    private static final String URL = "";

    public String[] getPalavras() {
        return null;
    }

    public String[] parseJSON() {
        return null;
    }

    public String getUrl(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
