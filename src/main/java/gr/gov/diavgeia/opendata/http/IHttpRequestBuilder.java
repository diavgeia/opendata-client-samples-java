package gr.gov.diavgeia.opendata.http;

import java.io.IOException;

/**
 *
 * @author Diavgeia Developers
 */
public interface IHttpRequestBuilder {
    void addCredentials(String username, String password);
    
    void addHeader(String name, String value);
    
    HttpResponse execute() throws IOException;
    
    String getAddress();

    boolean isHttps();

    boolean isVerifyServer();
}
