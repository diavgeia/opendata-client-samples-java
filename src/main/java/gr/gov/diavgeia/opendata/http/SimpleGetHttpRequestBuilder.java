package gr.gov.diavgeia.opendata.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Diavgeia Developers
 */
public class SimpleGetHttpRequestBuilder implements IHttpRequestBuilder {

    private final String CHARSET = "UTF-8";
    private final String address;
    private final boolean https;
    private final boolean verifyServer;
    private final Map<String, String> headers;
    private final Map<String, String> formFields;
    private final HttpURLConnection httpConnection;

    public SimpleGetHttpRequestBuilder(String address) throws MalformedURLException, IOException, URISyntaxException {
        this(address, true, false);
    }

    public SimpleGetHttpRequestBuilder(String address, boolean https, boolean verifyServer) throws MalformedURLException,
            IOException, URISyntaxException {
        if (https && !verifyServer) {
            overrideHttpsRestrictions();
        }
        URL url = new URL(address);
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
        this.address = uri.toASCIIString();
        this.https = https;
        this.verifyServer = verifyServer;
        this.headers = new HashMap<>();
        this.formFields = new HashMap<>();
        this.httpConnection = (HttpURLConnection) new URL(this.address).openConnection();
    }

    @Override
    public HttpResponse execute() throws IOException {
        // super.overrideHttpsRestrictions();
        _addHeaders(httpConnection);

        int status = httpConnection.getResponseCode();
        String message = httpConnection.getResponseMessage();

        if (status == HttpURLConnection.HTTP_OK) {
            return new HttpResponse(status, message, httpConnection.getInputStream());
        } else {
            return new HttpResponse(status, message, httpConnection.getErrorStream());
        }
    }

    @Override
    public void addCredentials(String username, String password) {
        String cred = username + ":" + password;
        try {
            this.addHeader("Authorization", "Basic " + DatatypeConverter.printBase64Binary(cred.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addHeader(String name, String value) {
         if (name != null && !name.trim().isEmpty()) {
            this.headers.put(name, value);
        }
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public boolean isHttps() {
        return this.https;
    }

    @Override
    public boolean isVerifyServer() {
        return this.verifyServer;
    }

    // PRIVATE ////////////////////
    private void _addHeaders(HttpURLConnection conn) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            conn.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    // PRIVATE ////////////////////
    
    private void overrideHttpsRestrictions() {
        try {
            trustAllHttpsCertificates();
            skipHostnameVerification();
            System.setProperty("jsse.enableSNIExtension", "false");
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    private static void skipHostnameVerification() {
        HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
            @Override
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                return true;
            }
        });

    }

    // Source: http://www.java-samples.com/showtutorial.php?tutorialid=211
    public static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
        }

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
        }
    }

    private static void trustAllHttpsCertificates() throws Exception {
        // Create a trust manager that does not validate certificate chains:
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
}
