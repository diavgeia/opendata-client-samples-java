package gr.gov.diavgeia.opendata.http;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

/**
 * 
 * @author Diavgeia Developers
 */
public class SimplePostHttpRequestBuilder extends HttpRequestBuilder {
    private HttpPost httpPost;
    private final List <NameValuePair> formFields = new ArrayList <>();
    
    public SimplePostHttpRequestBuilder(String address) {
        this(address, true, false);
        this.httpPost = new HttpPost(address);
    }

    public SimplePostHttpRequestBuilder(String address, boolean https, boolean verifyServer) {
        super(address, https, verifyServer);
    }
    
    @Override
    public void addHeader(String name, String value) {
        this.httpPost.addHeader(name, value);
    }
    
    public void addFormField(String name, String value) {
        this.formFields.add(new BasicNameValuePair(name, value));
    }

    @Override
    protected HttpResponse executeWithClient(CloseableHttpClient client, HttpClientContext clientContext) throws IOException {
        HttpResponse res = null;
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(this.formFields);
        httpPost.setEntity(entity);
        
        try (CloseableHttpResponse response = client.execute(super.getHost(), this.httpPost, clientContext)) {
            StatusLine status = response.getStatusLine();
            res = new HttpResponse(status.getStatusCode(), status.getReasonPhrase(), response.getEntity().getContent());
        }
        return res;
    }
}
