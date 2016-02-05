package org.xcraftman;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
/**
 * Created by Administrator on 2016/2/5.
 */
public class RequestTask implements Runnable{

    private ResponseHandler<String> responseHandler;
    private RequestConfig defaultRequestConfig;
    private CloseableHttpClient httpclient;

    public RequestTask(CloseableHttpClient httpclient) {

        this.defaultRequestConfig = RequestConfig.custom().
                setConnectTimeout(5000).
                setConnectionRequestTimeout(5000).
                setSocketTimeout(5000).build();

        this.responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;


                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };

        this.httpclient = httpclient;
    }

    @Override
    public void run() {
        HttpGet httpget = new HttpGet("http://172.26.69.7:8080/");
        httpget.setConfig(defaultRequestConfig);
        try {
            httpclient.execute(httpget,responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            httpget.releaseConnection();
        }

    }
}
