import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Get200 {

    CloseableHttpClient client;
    CloseableHttpResponse response;
    public static final String BASE_ENDPOINT = "http://dummy.restapiexample.com/api/v1/employees";

     //HttpClient client = HttpClientBuilder.create().build();             //Creating a object that does the sending

    @Before
    public void setUp()                                                             //Doing this at the beginning, opens a separate connection for each test
    {                                                                               //When 1ast test is run, opened the connection to the api, but never closed it
        client = HttpClientBuilder.create().build();
    }

    @Test
    public void baseURLReturn200() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);                             //Get request object, that request the URL
        response = client.execute(get);

        //Once the response is taken as an object, we need to extract the status code.
        // There is a method getStatusLine and getStatusCode() ---->
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(200, actualStatus);
    }

    @Test
    public void employee56103() throws IOException {
        HttpGet get = new HttpGet("http://dummy.restapiexample.com/api/v1/employee/56103" );        //I could use 'HttpGet(BASE_ENDPOINT + "endPoint_Name") but this time the URL is different since s in missing
        response = client.execute(get);

        //Once the response is taken as an object, we need to extract the status code.
        // There is a method getStatusLine and getStatusCode() ---->
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(200, actualStatus);
    }

    @Test
    public void employee56203() throws IOException {
        HttpGet get = new HttpGet("http://dummy.restapiexample.com/api/v1/employee/56203" );        //I could use 'HttpGet(BASE_ENDPOINT + "endPoint_Name") but this time the URL is different since s in missing
        response = client.execute(get);

        //Once the response is taken as an object, we need to extract the status code.
        // There is a method getStatusLine and getStatusCode() ---->
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(200, actualStatus);
    }


    @After
    public void closeResources() throws IOException                                 //Doing this at the beginning, opens a separate connection for each test
    {                                                                               //When 1st test is run, opened the connection to the api, but never closed it
        client.close();
        response.close();
    }
}
