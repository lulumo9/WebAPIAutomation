import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ExampleHeaders {

    String url = "https://api.thecatapi.com/v1/votes";

    @Test
    public void ResponseTimeValidation() throws IOException
    {
            HttpClient httpClient = HttpClientBuilder.create().build();                             //Use this instead

            HttpPost request = new HttpPost("https://api.thecatapi.com/v1/votes");
            StringEntity params = new StringEntity("{\"image_id\":\"LuisaTestingx\",\"sub_id\":\"9632\",\"value\":\"42\"}");
            request.addHeader("content-type", "application/json");
            request.addHeader("x-api-key", "1696f357-2331-45ca-914e-170424505e20");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            // handle response here...

            String jSonBody = EntityUtils.toString(response.getEntity());          //EntityUtils.toString will return the entire Json content of the body
            System.out.println(jSonBody);

            int actualStatus = response.getStatusLine().getStatusCode();
            Assert.assertEquals(200, actualStatus);
    }
}
