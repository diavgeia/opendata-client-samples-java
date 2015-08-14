package gr.gov.diavgeia.opendata.samples.decisions;

import java.io.InputStream;
import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.http.MultipartPostHttpRequestBuilder;

import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.util.JsonUtil;
import gr.gov.diavgeia.opendata.util.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;

public class EditDecisionAndAddAttachmentMultipart {
    private static final String METADATA_FILE_PATH = "/gr/gov/diavgeia/opendata/samples/decisions/EditDecision.json";
    
    private static final String ATTACHMENT_FILE_PATH = "/gr/gov/diavgeia/opendata/samples/decisions/Attachment.docx";
    
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String jsonString;
        try (InputStream jsonStream = EditDecisionAndAddAttachmentMultipart.class.getResourceAsStream(METADATA_FILE_PATH)) {
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
        
        addAttachment(post);
        
        HttpResponse response = post.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            System.out.println(body);
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
            System.out.println(StringUtil.readInputStream(response.getBody()));
        }
    }
    
    private static void addAttachment(MultipartPostHttpRequestBuilder post) throws IOException {
        List<String> attachmentDescriptions = new ArrayList<>();
        
        // Attachment.docx
        byte[] att1Bytes = null;
        try (InputStream attStream = PublishDecisionWithJsonString.class.getResourceAsStream(ATTACHMENT_FILE_PATH)) {
            att1Bytes = IOUtils.toByteArray(attStream);
            attachmentDescriptions.add("The first attachment is a Word file");
            // Add the rest of the attachment information in the binary part
            post.addFilePart("attachments", "Attachment.docx", att1Bytes, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
        
        post.addFormField("attachmentDescr", JsonUtil.toString(attachmentDescriptions));
    }
}
