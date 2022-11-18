package org.mindtickle.petstore.utils.http;

import okhttp3.*;
import okhttp3.Request.Builder;
import org.mindtickle.petstore.utils.CommonUtils;
import org.mindtickle.petstore.utils.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    private static int                  timeout     = 300;
    private static OkHttpClient.Builder httpClient  = new OkHttpClient.Builder()
                                                            .connectTimeout(timeout, TimeUnit.SECONDS)
                                                            .readTimeout(timeout, TimeUnit.SECONDS)
                                                            .writeTimeout(timeout, TimeUnit.SECONDS);

    private OkHttpClient                client      = httpClient.build();
    public static final MediaType       JSON        = MediaType.parse("application/json");

    private static boolean              networkLogs = true;

    public Response GET(String url, Map<String, String> headers) {

        Logger.info(networkLogs, "Url - " + url + "and Headers - " + CommonUtils.toPrettyJson(headers));
        Builder builder = new Builder().url(url);
        if(headers != null) {
            for(Entry<String, String> e : headers.entrySet()) {
                builder.addHeader(e.getKey(), e.getValue());
            }
        }

        Request request = builder.build();

        Response response;
        try {
            response = client.newCall(request).execute();
            return response;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response POST(String url, Map<String, String> headers, String payload) {
        Logger.info(networkLogs, "Url - " + url + " and Headers - " + CommonUtils.toPrettyJson(headers) + " and payload - " + payload);

        MediaType contentType = MediaType.parse(headers.get("Content-Type"));
		RequestBody body = RequestBody.create(contentType, payload.getBytes());

        Builder builder = new Builder();
        if(headers != null) {
            for(Entry<String, String> e : headers.entrySet()) {
                builder.addHeader(e.getKey(), e.getValue());
            }
        }

        Request request = builder.url(url).post(body).build();
        Logger.info(networkLogs, "RequestObject - " + CommonUtils.toPrettyJson(request));

        Response response;
        try {
            response = client.newCall(request).execute();
            return response;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response DELETE(String url, Map<String, String> headers, String payload) {
        Logger.info(networkLogs, "Url - " + url + " and Headers - " + CommonUtils.toPrettyJson(headers) + " and payload - " + payload);

        RequestBody body = RequestBody.create(JSON, payload.getBytes());

        Builder builder = new Builder();
        if(headers != null) {
            for(Entry<String, String> e : headers.entrySet()) {
                builder.addHeader(e.getKey(), e.getValue());
            }
        }

        Request request = builder.url(url).delete(body).build();
        Logger.info(networkLogs, "RequestObject - " + CommonUtils.toPrettyJson(request));

        Response response;
        try {
            response = client.newCall(request).execute();
            return response;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response PUT(String url, Map<String, String> headers, String payload) {
        Logger.info(networkLogs, "Url - " + url + " and Headers - " + CommonUtils.toPrettyJson(headers) + " and payload - " + payload);

        MediaType contentType = MediaType.parse(headers.get("Content-Type"));
        RequestBody body = RequestBody.create(contentType, payload.getBytes());

        Builder builder = new Builder();
        if(headers != null) {
            for(Entry<String, String> e : headers.entrySet()) {
                builder.addHeader(e.getKey(), e.getValue());
            }
        }

        Request request = builder.url(url).put(body).build();
        Logger.info(networkLogs, "RequestObject - " + CommonUtils.toPrettyJson(request));

        Response response;
        try {
            response = client.newCall(request).execute();
            return response;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
