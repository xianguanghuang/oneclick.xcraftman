package impatient.springboot.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;


/**
 * Created by Administrator on 2016/2/5.
 */

public class ResponseLeak  {





    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig defaultRequestConfig = RequestConfig.custom().
                setConnectTimeout(5000).
                setConnectionRequestTimeout(5000).
                setSocketTimeout(5000).build();
        try {




            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        // HttpEntity entity = response.getEntity();
                        //return entity != null ? EntityUtils.toString(entity) : null;
                        return "";
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            while (true){
                HttpGet httpget = new HttpGet("http://localhost:8080/");
                httpget.setConfig(defaultRequestConfig);
                httpclient.execute(httpget, responseHandler);
            }
        } finally {
            httpclient.close();
        }
    }
}
