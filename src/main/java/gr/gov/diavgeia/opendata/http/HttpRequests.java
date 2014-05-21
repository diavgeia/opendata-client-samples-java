package gr.gov.diavgeia.opendata.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * 
 * @author Diavgeia Developers
 */
public class HttpRequests {
    public static MultipartPostHttpRequestBuilder postMultipart(String targetUrl) throws MalformedURLException,
            IOException, URISyntaxException {
        return new MultipartPostHttpRequestBuilder(targetUrl);
    }
    
    public static SimpleGetHttpRequestBuilder post(String targetUrl) throws MalformedURLException, IOException,
            URISyntaxException {
        return new SimpleGetHttpRequestBuilder(targetUrl);
    }
    
    public static RawPostHttpRequestBuilder postRaw(String targetUrl) throws MalformedURLException, IOException,
            URISyntaxException {
        return new RawPostHttpRequestBuilder(targetUrl);
    }
    
    public static SimpleGetHttpRequestBuilder get(String targetUrl) throws MalformedURLException, IOException,
            URISyntaxException {
        return new SimpleGetHttpRequestBuilder(targetUrl);
    }
}
