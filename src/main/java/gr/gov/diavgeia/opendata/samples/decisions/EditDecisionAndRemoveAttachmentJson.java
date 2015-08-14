package gr.gov.diavgeia.opendata.samples.decisions;

import java.io.InputStream;
import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.http.RawPostHttpRequestBuilder;

import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.util.StringUtil;

public class EditDecisionAndRemoveAttachmentJson {
    private static final String METADATA_FILE_PATH = "/gr/gov/diavgeia/opendata/samples/decisions/EditPublishedDecisionAndRemoveAttachment.json";
    
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String jsonString;
        try (InputStream jsonStream = EditDecisionAndRemoveAttachmentJson.class.getResourceAsStream(METADATA_FILE_PATH)) {
            jsonString = StringUtil.readInputStream(jsonStream);
        }
        
        // Prepare POST
        RawPostHttpRequestBuilder post = HttpRequests.postRaw(conf.getBaseUrl() + "/decisions/Ρ4ΙΞΝ-9ΜΝ");
        if (conf.isAuthenticationEnabled()) {
            post.addCredentials(conf.getUsername(), conf.getPassword());
        }
        // post.addCredentials("10599_api", "User@10599");
        post.addHeader("Accept", "application/json");
        post.setJsonString(jsonString);
        
        HttpResponse response = post.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            System.out.println(body);
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
            System.out.println(StringUtil.readInputStream(response.getBody()));
        }
    }
}
