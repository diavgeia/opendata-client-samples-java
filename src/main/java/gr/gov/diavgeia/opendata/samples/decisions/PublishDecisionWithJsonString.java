package gr.gov.diavgeia.opendata.samples.decisions;

import java.io.InputStream;
import java.net.HttpURLConnection;

import org.apache.commons.io.IOUtils;

import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.http.MultipartPostHttpRequestBuilder;
import gr.gov.diavgeia.opendata.json.Error;
import gr.gov.diavgeia.opendata.json.Errors;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.util.JsonUtil;
import gr.gov.diavgeia.opendata.util.StringUtil;


/**
 *
 * @author Diavgeia Developers
 */
public class PublishDecisionWithJsonString {
    
    private static final String METADATA_FILE_PATH = "/gr/gov/diavgeia/opendata/samples/decisions/PublishDecision.json";
    //private static final String METADATA_FILE_PATH = "/gr/gov/diavgeia/opendata/samples/decisions/PublishDecision_Errors.json";
    private static final String PDF_FILE_PATH = "/gr/gov/diavgeia/opendata/samples/decisions/PublishDecision.pdf";
    
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String jsonString;
        try (InputStream jsonStream = PublishDecisionWithJsonString.class.getResourceAsStream(METADATA_FILE_PATH)) {
            jsonString = StringUtil.readInputStream(jsonStream);
        }
        byte[] pdfContent;
        try (InputStream pdfStream = PublishDecisionWithJsonString.class.getResourceAsStream(PDF_FILE_PATH)) {
            pdfContent = IOUtils.toByteArray(pdfStream);
        }
        
        // Prepare POST
        MultipartPostHttpRequestBuilder post = HttpRequests.postMultipart(conf.getBaseUrl() + "/decisions");
        if (conf.isAuthenticationEnabled()) {
            post.addCredentials(conf.getUsername(), conf.getPassword());
        }
        // post.addCredentials("10599_api", "User@10599");
        post.addHeader("Accept", "application/json");
        
        
        post.addFormField("metadata", jsonString);
        post.addFilePart("decisionFile", "decision.pdf", pdfContent, "application/pdf");
        
        HttpResponse response = post.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            System.out.println(body);
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
            if (response.getStatusCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
                String errorBody = StringUtil.readInputStream(response.getBody());
                Errors errors = JsonUtil.fromString(errorBody, Errors.class);
                for (Error err: errors.getErrors()) {
                    System.out.println(String.format("%s: %s", err.getErrorCode(), err.getErrorMessage()));
                }
            }
        }
    }
}
