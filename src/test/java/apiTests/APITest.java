package apiTests;

import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;
import utils.RestUtil;

public class APITest {

    @Test(description = "Positive test for countryCode= USA and valid stateCode=ID")
    public void  positiveTest(){
       HttpGet httpget = RestUtil.createRequest("http://services.groupkt.com/state/get/USA/ID");


    }
}
