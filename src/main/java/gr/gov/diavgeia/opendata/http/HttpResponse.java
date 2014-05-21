package gr.gov.diavgeia.opendata.http;

import java.io.InputStream;
import java.util.Map;

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
}
