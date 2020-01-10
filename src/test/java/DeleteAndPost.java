

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import static entitites.User.*;

import java.io.IOException;

public class DeleteAndPost extends BaseClass {

    @Test
    public void ableToDeleteAnEmployee_DELETE() throws IOException
    {
        HttpDelete request = new HttpDelete("http://dummy.restapiexample.com/api/v1/delete/63886");

        //Server understood what you wanted to delete but refused to execute since not any credentials has been provided
        //request.setHeader(HttpHeaders.AUTHORIZATION, "Token " + Credentials.TOKEN);
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(200, actualStatusCode);
        //I have checked manually on Postman and once, I delete in database, status code returned is 200 (instead of 204) but successful message is shown in the same page
        //I have deleted the employee 56711 'easily since I do not need to login for this dummy site

        //If status code is 200 means that has been deleted successful
        //But we can also test the body on that response
        String jSonBody = EntityUtils.toString(response.getEntity());            //EntityUtils.toString will return the entire Json content of the body
        Assert.assertTrue("Something went wrong", jSonBody.contains("successfully! deleted Records"));
        System.out.println(jSonBody);
    }


    @Test
    public void ableToCreateNewEmployee_POST() throws IOException {
        //Create an HttpPost with a valid EndPoint
        HttpPost request = new HttpPost("http://dummy.restapiexample.com/api/v1/create");


        //Set the basic Auth header:
        /*String auth = Credentials.EMAIL + ":" + Credentials.PASSWORD;           //Supplying your login with password separated by a semicolon. Credentials for the site, if needed are provided
        This password needs to be encoded into Base64 --->
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));     //This line of code returns an array of bytes that needs to be converted back into a String --->
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, "Token " + Credentials.TOKEN );
        */

        //Define JSON to post and set as Entity
        String jSon;
        jSon = "{\"" + EMPLOYEE_NAME + "\":\"" + properties.getProperty("newEmployeeName") + "\",\"" +
                EMPLOYEE_SALARY + "\":\"" + properties.getProperty("newEmployeeSalary") + "\",\"" +
                EMPLOYEE_AGE + "\":\"" + properties.getProperty("newEmployeeAge") + "\"}";
        request.setEntity(new StringEntity(jSon, ContentType.APPLICATION_JSON));

        //Send
        response = client.execute(request);
        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(200, actualStatusCode);
        //I have checked manually on Postman and once, I created a new record in database, status code returned is 200 (instead of 201)
        System.out.println("New employee saved in DB");
    }


    @Test
    public void printing()
    {
        Boolean print = true;
        Assert.assertTrue("Failed print is not true", print.equals(true));
        System.out.println("You did it \'great\'");
    }
}