package apiTests;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestUtils;

import java.io.IOException;


public class APITest {
    public String positiveURL = "http://services.groupkt.com/state/get/USA/ID";
    public String negativeURL = "http://services.groupkt.com/state/get/USA/Belarus";


    @Test(description = "Verify response is not null ")
    public void notEmptyHTTPResponseTest() throws IOException {
        CloseableHttpResponse serverResponse = RestUtils.getResponse( positiveURL );
        Assert.assertNotNull( serverResponse );
    }

    @Test(description = "Verify response is 200 for countryCode = USA and valid stateCode=ID", priority = 1)
    public void positiveHTTPResponseStatusTest() throws IOException {
        CloseableHttpResponse serverResponse = RestUtils.getResponse( positiveURL );
        Assert.assertEquals( serverResponse.getStatusLine().getStatusCode(), 200 );
    }

    @Test(description = "Verify capital is Boise for countryCode = USA and valid stateCode=ID", priority = 200)
    public void positiveCapitalTest() throws IOException {
        CloseableHttpResponse serverResponse = RestUtils.getResponse( positiveURL );
        String responseString = RestUtils.getResponseAsString( serverResponse );
        boolean checkCapital = responseString.contains( "Boise" );
        Assert.assertTrue( checkCapital );
    }

    @Test(description = "Verify response is 200 for countryCode = USA and invalid stateCode = Belarus")
    public void negativeHTTPResponseStatusTest() throws IOException {
        CloseableHttpResponse serverResponse = RestUtils.getResponse( negativeURL );
        Assert.assertEquals( serverResponse.getStatusLine().getStatusCode(), 200 );
    }

    @Test(description = "Verify capital is Boise for countryCode = USA and valid stateCode=ID", priority = 200)
    public void correctValidationErrorTest() throws IOException {
        CloseableHttpResponse serverResponse = RestUtils.getResponse( negativeURL );
        String responseString = RestUtils.getResponseAsString( serverResponse );
        Boolean errorMsg = responseString.contains( "No matching state found for requested code" );
        Assert.assertTrue( errorMsg );
    }
}
