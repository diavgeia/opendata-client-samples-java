package gr.gov.diavgeia.opendata.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;

public class RawPostHttpRequestBuilder extends HttpRequestBuilder {
    private final HttpPost httpPost;
    private EntityBuilder entityBuilder;
    private String jsonString;

    public RawPostHttpRequestBuilder(String address, boolean https, boolean verifyServer) {
        super(address, https, verifyServer);
        this.httpPost = new HttpPost(address);
        this.entityBuilder = EntityBuilder.create()
            .setContentEncoding("UTF-8")
            .setContentType(ContentType.APPLICATION_JSON);
    }

    public RawPostHttpRequestBuilder(String address) {
        this(address, true, false);
    }

    @Override
    public void addHeader(String name, String value) {
        httpPost.addHeader(name, value);
    }

    @Override
    protected HttpResponse executeWithClient(CloseableHttpClient client, HttpClientContext clientContext)
            throws IOException {
        HttpResponse res = null;
        
        HttpEntity requestEntity = this.entityBuilder.setText(jsonString).build();
        this.httpPost.setEntity(requestEntity);
        
        try (CloseableHttpResponse response = client.execute(super.getHost(), this.httpPost, clientContext)) {
            StatusLine status = response.getStatusLine();
            res = new HttpResponse(status.getStatusCode(), status.getReasonPhrase(), response.getEntity().getContent(), HttpResponse.getHeadersFromResponse(response));
        }
        
        return res;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
