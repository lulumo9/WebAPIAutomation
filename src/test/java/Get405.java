import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Get405 {

    CloseableHttpClient client;
    CloseableHttpResponse response;
    public static final String BASE_ENDPOINT = "https://api.github.com";

    //HttpClient client = HttpClientBuilder.create().build();             //Creating a object that does the sending

    @Before
    public void setUp()                                                             //Doing this at the beginning, opens a separate connection for each test
    {                                                                               //When 1st test is run, opened the connection to the api, but never closed it
        client = HttpClientBuilder.create().build();
    }

    @Test
    public void URLReturns404NonExistingURL() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexisting");                             //Get request object, that request the URL
        response = client.execute(get);

        //Once the response is taken as an object, we need to extract the status code.
        // There is a method getStatusLine and getStatusCode() ---->
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(404, actualStatus);
    }
}
