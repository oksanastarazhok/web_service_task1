package utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RestUtils {
   public CloseableHttpResponse serverResponse;

    public RestUtils(CloseableHttpResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    /**
     * This method creates request and returns response from a server in String format
     * @param baseURL URL that is need to be verified
     * @return response from a server
     * @throws IOException
     */


    public static CloseableHttpResponse getResponse(String baseURL) throws IOException {
        HttpGet httpGet = new HttpGet(baseURL);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse serverResponse = httpclient.execute(httpGet);
        return serverResponse;

    }

    /**This method maps serverResponse to String
     * @param serverResponse
     * @return String Object
     * @throws IOException
     */
public static String getString(CloseableHttpResponse serverResponse)throws IOException {
    HttpEntity entity = serverResponse.getEntity();
    String responseString = EntityUtils.toString(entity, "UTF-8");
    return responseString;
}

   }