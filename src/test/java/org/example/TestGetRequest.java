package org.example;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetRequest {

    @Test
    public void testUrl() throws IOException {
        Response response = sendGetRequest_authorize();

        assertEquals(200, response.code());
    }


    public static Response sendGetRequest_authorize() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url("https://regres.in/api/single_user")
                .build();

        Call call = client.newCall(request);

        return call.execute();
    }
}
