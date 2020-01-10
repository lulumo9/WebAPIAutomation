import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestingRESTAssured extends BaseClass{

    @Test
    public void canGetLuke()
    {

        RestAssured.get("http://swapi.co/api/people/1/?format=json").
                then().
                assertThat().
                body("skin_color",
                        equalTo("fair"));
    }
    // use RestAssured to make call then return the Response
    // use JsonPath to parse the JSON in response
    // JsonPath can be imported and used separately from RestAssured if required


    @Test
    //Using JsonPath to get the whole body and check for the value search
    public void canGetC3POandParseWithJsonPath(){

        // use RestAssured to make an HTML Call
        Response response = RestAssured.get(
                "http://swapi.co/api/people/2/?format=json").
                andReturn();

        String json = response.getBody().asString();                //Putting the whole body into a String!
        System.out.println(json);

        // Use the JsonPath parsing library of RestAssured to Parse the JSON
        JsonPath jsonPath = new JsonPath(json);                                   //JsonPath library is already included in RestAssured
        Assert.assertEquals("C-3PO", jsonPath.getString("name"));
    }
}
