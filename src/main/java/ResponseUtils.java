import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.Arrays;
import java.util.List;

public class ResponseUtils {

    public static String getHeader(CloseableHttpResponse response, String headerName)
    {
        //Get all the headers
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaters = Arrays.asList(headers);
        String returnHeader = "";

        //Loop over the header list
        for (Header header : httpHeaters) {
            if (headerName.equalsIgnoreCase(header.getName()))
            {
                returnHeader = header.getValue();
            }
        }

        //If no header has been found - throw and exception

        if (returnHeader.isEmpty())
        {
            throw  new RuntimeException("Did not find the header: " + headerName);
        }

        //Return header
        return  returnHeader;
    }

    public static String getHeaderJava8Way(CloseableHttpResponse response, final String headerName)
    {
        List<Header> httHeaders = Arrays.asList(response.getAllHeaders());

        Header matchHeader = httHeaders.stream()                                                                        //A list of headers is streamed, the headers "flow" in a stream one by one
                            .filter(header -> headerName.equalsIgnoreCase(header.getName()))                            //Here we filter by the header "we care about" - headerName
                            .findFirst().orElseThrow(() -> new RuntimeException("Did not find the header"));            //Find and return the 'first' header that matched the filter criteria, throw and exception if not any

        return matchHeader.getValue();                                                                                  //Return the string value of the header
    }

    public static boolean isHeaderPresent(CloseableHttpResponse response, final String headerName)
    {
        List<Header> httHeaders = Arrays.asList(response.getAllHeaders());                                              //A list of headers is streamed, the headers "flow" in a stream one by one

        return  httHeaders.stream()
                .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));                                     //We'd like ot get a match "any" that is equal to the one as parameter
    }
}
