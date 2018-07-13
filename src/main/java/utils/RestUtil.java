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
     * This method creates request
     * @param baseURL URL that is need to be verified
     */
    public static HttpGet createRequest(String baseURL) {
        HttpGet httpGet = new HttpGet(baseURL);
        return httpGet;
    }


    /**
     * This method returns response from a server in String format
     * @param httpGet request to a server
     * @return response from a server
     * @throws IOException
     */
    public static String getResponse(HttpGet httpGet) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        return responseString;
    }

    /**
     * Reset Base URI (after test)
     * @param baseURL
     */
    public static void resetBaseURL(String baseURL) {
        baseURL = null;
    }

}
