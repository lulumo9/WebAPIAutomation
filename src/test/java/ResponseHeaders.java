import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class ResponseHeaders {

    CloseableHttpClient client;
    CloseableHttpResponse response;
    public static final String BASE_ENDPOINT = "http://dummy.restapiexample.com/api/v1/employees";

    //HttpClient client = HttpClientBuilder.create().build();             //Creating a object that does the sending

    @Before
    public void setUp()                                                             //Doing this at the beginning, opens a separate connection for each test
    {                                                                               //When 1st test is run, opened the connection to the api, but never closed it
        client = HttpClientBuilder.create().build();
    }

    @Test
    public void getContentType() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);                             //Get request object, that request the URL
        response = client.execute(get);

        //Once the response is taken as an object
        // There is a method getEntity() and getContentType ---->
        Header contentType = response.getEntity().getContentType();
        Assert.assertEquals("text/html; charset=UTF-8", contentType.getValue());
        System.out.println("Test passed! content type is: " + contentType);


        //In case, you only wanna take the mediaType without the charset, you can do:
        ContentType cType = ContentType.getOrDefault(response.getEntity());
        Assert.assertEquals("text/html", cType.getMimeType());
        System.out.println("Test passed! media type is: " + cType.getMimeType());
        //Charset value can also be taken:    Assert.assertEquals(cType.getCharset()
    }


    //There is not a method to get all the header, but writing our 'own header extractor' code, we can use it for the rest***
    @Test
    public void getServerValue() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);                             //Get request object, that request the URL
        response = client.execute(get);

        String headerValue = ResponseUtils.getHeader(response, "Server" );            //Creating method getHeader***
        Assert.assertEquals("Apache", headerValue);
    }

    @Test
    public void isPragmaHeaderNoCache() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);                             //Get request object, that request the URL
        response = client.execute(get);

        String headerValue = ResponseUtils.getHeaderJava8Way(response, "Pragma" );            //Creating method getHeader***
        Assert.assertEquals("no-cache", headerValue);
    }


    //This one will failed since ETAG header is not present on the API selected
    @Test
    public void ETagIsPresent() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);                             //Get request object, that request the URL
        response = client.execute(get);

        boolean IsPresent = ResponseUtils.isHeaderPresent(response, "ETag" );            //Creating method getHeader***
        System.out.println("The header is preset:" + IsPresent);
        Assert.assertTrue(IsPresent);
    }
}
