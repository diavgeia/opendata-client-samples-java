package gr.gov.diavgeia.opendata.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author Diavgeia Developers
 */
public class MultipartPostHttpRequestBuilder extends HttpRequestBuilder {
    
    private static final Charset UTF8 = Charset.forName("UTF-8");

    private HttpPost post;
    private MultipartEntityBuilder multipartBuilder;

    public MultipartPostHttpRequestBuilder(String address) {
        this(address, true, false);
    }

    public MultipartPostHttpRequestBuilder(String address, boolean https, boolean verifyServer) {
        super(address, https, verifyServer);
        multipartBuilder = MultipartEntityBuilder.create();
        multipartBuilder.setCharset(UTF8);
        this.post = new HttpPost(address);
    }

    @Override
    protected HttpResponse executeWithClient(CloseableHttpClient client, HttpClientContext clientContext) throws IOException {
        HttpResponse res = null;
        HttpEntity httpEntity = this.multipartBuilder.build();
        this.post.setEntity(httpEntity);
        try (CloseableHttpResponse response = client.execute(super.getHost(), post, clientContext)) {
            StatusLine status = response.getStatusLine();
            HttpEntity responseEntity = response.getEntity();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            responseEntity.writeTo(bos);
            bos.flush();
            bos.close();
            EntityUtils.consumeQuietly(httpEntity);
            res = new HttpResponse(status.getStatusCode(), status.getReasonPhrase(), new ByteArrayInputStream(bos.toByteArray()));
        }
        return res;
    }
    
    public void addFormField(String name, String value) {
        if (name != null && !name.trim().isEmpty()) {
            try {
                multipartBuilder.addPart(name, new StringBody(value, "text/plain", UTF8));
            } catch (UnsupportedEncodingException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public void addHeader(String name, String value) {
        post.addHeader(name, value);
    }

    public void addFilePart(String fieldName, File file) throws IOException {
        multipartBuilder.addBinaryBody(fieldName, file);
    }

    public void addFilePart(String fieldName, FilePart filePart) {
        this.addFilePart(fieldName, filePart.getFilename(), filePart.getContent(), filePart.getContentType());
    }

    public void addFilePart(String fieldName, String filename, byte[] content, String contentType) {
        multipartBuilder.addBinaryBody(fieldName, content, ContentType.parse(contentType), filename);
    }
}
