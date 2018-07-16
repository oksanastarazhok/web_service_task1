package apiTests;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestUtil;

import java.io.IOException;


public class APITest {



    @Test(description = "Verify response is 200 for countryCode = USA and valid stateCode=ID", priority = 1)
    public void positiveHTTPResponseStatusTest() throws IOException {
        CloseableHttpResponse serverResponse = RestUtil.getResponse("http://services.groupkt.com/state/get/USA/ID");
        Assert.assertEquals(serverResponse.getStatusLine().getStatusCode(), 200);
        System.out.println("----------------------------------------");
        System.out.println("Response status code is " + serverResponse.getStatusLine().getStatusCode());


    }

    @Test(description = "Verify capital is Boise for countryCode = USA and valid stateCode=ID", priority = 200)
    public void positiveCapitalTest(CloseableHttpResponse serverResponse) throws IOException {
        String responseString = RestUtil.getString(serverResponse);
        boolean checkCapital = responseString.contains("Boise");
        Assert.assertTrue(checkCapital);
    }


}
