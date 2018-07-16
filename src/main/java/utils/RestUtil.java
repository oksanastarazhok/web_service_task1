package utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RestUtil {

      /**
     * This method creates request and returns response from a server in String format
     * @param baseURL URL that is need to be verified
     * @return response from a server
     * @throws IOException
     */
    public static CloseableHttpResponse getResponse(String baseURL) throws IOException {
        HttpGet httpGet = new HttpGet(baseURL);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpGet);
        return response;

    }

public static String getString(CloseableHttpResponse response)throws IOException {
    HttpEntity entity = response.getEntity();
    String responseString = EntityUtils.toString(entity, "UTF-8");
    return responseString;
}
}
