package gr.gov.diavgeia.opendata.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Logger;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * 
 * @author Diavgeia Developers
 */
public abstract class HttpRequestBuilder implements IHttpRequestBuilder {
    private static final Logger LOG = Logger.getLogger(HttpRequestBuilder.class.getName());
    
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    
    private final String address;
    private final URI uri;
    private final boolean https;
    private final boolean verifyServer;
    
    private HttpHost host;
    private CredentialsProvider credentialsProvider;

    public HttpRequestBuilder(String address) {
        this(address, true, false);
    }

    public HttpRequestBuilder(String address, boolean https, boolean verifyServer) {
        this.https = https;
        this.verifyServer = verifyServer;
        try {
            URL url = new URL(address);
            this.uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), url.getRef());
            this.address = uri.toASCIIString();
            this.host = new HttpHost(uri.getHost(), url.getPort(), uri.getScheme());
        } catch (MalformedURLException | URISyntaxException e) {
            throw new IllegalArgumentException("Invalid HTTP address");
        }
    }
    
    @Override
    public final void addCredentials(String username, String password) {
        this.credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(host.getHostName(), host.getPort()),
                new UsernamePasswordCredentials(username, password));
    }
    
    @Override
    public final HttpResponse execute() throws IOException {
        HttpResponse res = null;

        HttpClientContext clientContext = HttpClientContext.create();
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        _prepareAuth(clientBuilder, clientContext);
        _prepareSSL(clientBuilder);

        try (CloseableHttpClient client = clientBuilder.build()) {
            org.apache.http.client.methods.HttpPost a;
            res = executeWithClient(client, clientContext);
        }

        return res;
    }
    
    @Override
    public abstract void addHeader(String name, String value);
    
    protected abstract HttpResponse executeWithClient(CloseableHttpClient client, HttpClientContext clientContext) throws IOException;

    // GETTERS ////////////////////
    
    @Override
    public String getAddress() {
        return address;
    }

    public static Charset getCharset() {
        return DEFAULT_CHARSET;
    }

    public URI getUri() {
        return uri;
    }

    @Override
    public boolean isHttps() {
        return https;
    }

    @Override
    public boolean isVerifyServer() {
        return verifyServer;
    }

    public HttpHost getHost() {
        return host;
    }

    public CredentialsProvider getCredentialsProvider() {
        return credentialsProvider;
    }
    
    // PRIVATE ////////////////////
    
    private static X509TrustManager TRUST_ALL_TRUST_MANAGER = new X509TrustManager() {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] certs,
                String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] certs,
                String authType) {
        }
    };
    
    private void _prepareAuth(HttpClientBuilder clientBuilder, HttpClientContext clientContext) {
        if (this.credentialsProvider != null) {
            clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            AuthCache authCache = new BasicAuthCache();
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(host, basicAuth);
            clientContext.setAuthCache(authCache);
        }
    }

    private void _prepareSSL(HttpClientBuilder clientBuilder) {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, new TrustManager[]{TRUST_ALL_TRUST_MANAGER}, new SecureRandom());
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                    verifyServer ? SSLConnectionSocketFactory.STRICT_HOSTNAME_VERIFIER : SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            clientBuilder.setSSLSocketFactory(sslConnectionSocketFactory);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new IllegalStateException(e);
        }
    }
}
