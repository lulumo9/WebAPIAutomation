import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import static entitites.User.ID;

public class BodyTestWithSimpleMap extends BaseClass {

    @Test
    public void returnsCorrectLogin() throws IOException
    {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
        response = client.execute(get);

        //Now we know the body is nested inside the 'entity' object, which itself is nested inside the 'response' object.
        String jSonBody = EntityUtils.toString(response.getEntity());                                                           //EntityUtils.toString will return the entire Json content of the body
        //System.out.println(jSonBody);

        //After adding the Json dependency in order to convert the string in an 'object that has structure'
        JSONObject jsonObject = new JSONObject(jSonBody);               //I can create a Json object and pass the string to this one
        //The Json object is a simpleMap, with keys and values

        //By calling getValueFor, I am querying the map by a key and getting the exact value that I want, like in this case "login"
        //Then taking this one to the string variable
        String loginValue = (String) getValueFor(jsonObject, "login");

        Assert.assertEquals("andrejss88", loginValue);
    }

    //Testing with ID, being a constant in entities folder
    @Test
    public void returnsCorrectId() throws IOException
    {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
        response = client.execute(get);

        //Now we know the body is nested inside the 'entity' object, which itself is nested inside the 'response' object.
        String jSonBody = EntityUtils.toString(response.getEntity());                                                           //EntityUtils.toString will return the entire Json content of the body
        //System.out.println(jSonBody);

        //After adding the Json dependency in order to convert the string in an 'object that has structure'
        JSONObject jsonObject = new JSONObject(jSonBody);               //I can create a Json object and pass the string to this one
        //The Json object is a simpleMap, with keys and values

        //By calling getValueFor, I am querying the map by a key and getting the exact value that I want, like in this case "login"
        //Then taking this one to the Integer variable
        Integer loginValue = (Integer) getValueFor(jsonObject, ID);

        Assert.assertEquals(Integer.valueOf(11834443), loginValue);
    }

    private Object getValueFor(JSONObject jsonObject, String key)                               //Method takes 2 parameters (the JSonObject and the key by which we wanna search
    {
        return jsonObject.get(key);                 //I am returning a type 'Object' because the actual value might be any type (String, boolean, int, ...)
    }
}


