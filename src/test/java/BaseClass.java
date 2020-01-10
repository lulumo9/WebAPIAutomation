import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    CloseableHttpClient client;
    CloseableHttpResponse response;
    public static final String BASE_ENDPOINT = "https://api.github.com";

    //HttpClient client = HttpClientBuilder.create().build();             //Creating a object that does the sending

    Properties properties = new Properties();
    {
        try {
            //Loading the config properties file
            properties.load(new FileInputStream("C:\\Users\\Luisa_Fernanda_Munoz\\IdeaProjects\\WebAPIAutomation\\src\\test\\java\\config.properties"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp()                                                             //Doing this at the beginning, opens a separate connection for each test
    {                                                                               //When 1st test is run, opened the connection to the api, but never closed it
        client = HttpClientBuilder.create().build();
    }

}
