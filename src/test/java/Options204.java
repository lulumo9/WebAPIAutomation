import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class Options204 extends BaseClass {

    @Test
    public void optionsReturnsCorrectMethodsList() throws IOException {

        String header = "Access-Control-Allow-Methods";
        String expectedReply = "GET, POST, PATCH, PUT, DELETE";

        HttpOptions request = new HttpOptions(BASE_ENDPOINT);
        response = client.execute(request);

        String actualValue = ResponseUtils.getHeader(response, header);                 //Extracting the header 'Access-Control-Allow-Methods' and taking it to a string
        Assert.assertEquals(expectedReply, actualValue);
        //If passed, we can be sure that POST and DELETE its supported
    }
}
