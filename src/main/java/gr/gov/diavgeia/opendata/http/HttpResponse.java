package gr.gov.diavgeia.opendata.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * 
 * @author Diavgeia Developers
 */
public class HttpResponse {
    private int statusCode;
    private String statusMessage;
    private InputStream body;
    private Map<String, String> headers;
    
    public HttpResponse() {
    }
    
    public HttpResponse(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
    
    public HttpResponse(int statusCode, String statusMessage, InputStream body) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.body = body;
    }
    
    public HttpResponse(int statusCode, String statusMessage, InputStream body, Map<String, String> headers) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.body = body;
        this.headers = headers;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
    public String getStatusMessage() {
        return statusMessage;
    }
    
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    public InputStream getBody() {
        return body;
    }
    
    public void setBody(InputStream body) {
        this.body = body;
    }
    
    public Map<String, String> getHeaders() {
        return headers;
    }
    
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    
    /**
     * Returns all headers as a Map from an HTTP response.
     *
     * @param response a {@link CloseableHttpResponse} implementation object.
     * @return a Map which has headerName as key and headerValue as value.
     */
    public static Map<String, String> getHeadersFromResponse(CloseableHttpResponse response) {
        Map<String, String> myHeaders = new HashMap<String, String>();

        if (response == null) {
            return myHeaders;
        }

        if (response.getAllHeaders() != null) {
            for (Header i : response.getAllHeaders()) {
                myHeaders.put(i.getName(), i.getValue());
            }
        }

        return myHeaders;
    }

    /**
     * Returns all headers as a Map from an HTTP response. Used a <a
     * href="http://www.mkyong.com/java/how-to-get-http-response-header-in-java/">Mkyong
     * post</a> as help.
     *
     *
     * @param urlConnection a {@link HttpURLConnection} object containing the
     * HTTP response.
     * @return a Map which has headerName as key and headerValue as value.
     */
    public static Map<String, String> getHeadersFromUrlConnectionResponse(HttpURLConnection urlConnection) {
        Map<String, String> myHeaders = new HashMap<String, String>();

        if (urlConnection == null) {
            return myHeaders;
        }

        //get all headers
        Map<String, List<String>> map = urlConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {

            if (entry.getValue() != null) {
                for (String i : entry.getValue()) {
                    myHeaders.put(entry.getKey(), i);
                }
            }
        }

        return myHeaders;
    }
}
