package apiTests;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestUtils;

import java.io.IOException;


public class APITest {
public String positiveURL = "http://services.groupkt.com/state/get/USA/ID";
public String negativeURL = "http://services.groupkt.com/state/get/USA/Belarus";


    @Test(description = "Verify response is 200 for countryCode = USA and valid stateCode=ID", priority = 1)
    public void positiveHTTPResponseStatusTest() throws IOException {
        CloseableHttpResponse serverResponse = RestUtils.getResponse("http://services.groupkt.com/state/get/USA/ID");
        Assert.assertEquals(serverResponse.getStatusLine().getStatusCode(), 200);
        System.out.println("----------------------------------------");
        System.out.println("Response status code is " + serverResponse.getStatusLine().getStatusCode());


    }

    @Test(description = "Verify capital is Boise for countryCode = USA and valid stateCode=ID", priority = 200)
    public void positiveCapitalTest() throws IOException {
        CloseableHttpResponse serverResponse = RestUtils.getResponse("http://services.groupkt.com/state/get/USA/ID");
        String responseString = RestUtils.getString(serverResponse);
        boolean checkCapital = responseString.contains("Boise");
        Assert.assertTrue(checkCapital);
    }


}
