package gr.gov.diavgeia.opendata.samples.decisions;

import java.io.InputStream;
import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.http.MultipartPostHttpRequestBuilder;

import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.util.StringUtil;

public class EditDecisionAndRemoveAttachmentMultipart {
    private static final String METADATA_FILE_PATH = "/gr/gov/diavgeia/opendata/samples/decisions/EditDecision.json";
    
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String jsonString;
        try (InputStream jsonStream = EditDecisionAndRemoveAttachmentMultipart.class.getResourceAsStream(METADATA_FILE_PATH)) {
            jsonString = StringUtil.readInputStream(jsonStream);
        }
        
        // Prepare POST
        MultipartPostHttpRequestBuilder post = HttpRequests.postMultipart(conf.getBaseUrl() + "/decisions/Ρ4ΙΞΝ-9ΜΝ");
        if (conf.isAuthenticationEnabled()) {
            post.addCredentials(conf.getUsername(), conf.getPassword());
        }
        // post.addCredentials("10599_api", "User@10599");
        post.addHeader("Accept", "application/json");

        post.addFormField("metadata", jsonString);
        
        // Set attachment IDs to remove
        post.addFormField("attachmentsToRemove", "[\"f75bc83c-9337-4289-9fdc-bf3ce6e47830\", \"58ecb7dc-5a40-41e9-9c20-b80922cd85ba\", \"9773e628-0e65-4a63-8d2c-878b0076f9fb\"]");
        
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
