package gr.gov.diavgeia.opendata.samples.decisions;

import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;

import org.apache.commons.io.IOUtils;

import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.http.MultipartPostHttpRequestBuilder;
import gr.gov.diavgeia.opendata.json.DecisionStoreRequest;
import gr.gov.diavgeia.opendata.json.AttachmentStoreRequest;
import gr.gov.diavgeia.opendata.json.Decision;
import gr.gov.diavgeia.opendata.json.Errors;
import gr.gov.diavgeia.opendata.json.Error;

import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.util.JsonUtil;
import gr.gov.diavgeia.opendata.util.StringUtil;
import static gr.gov.diavgeia.opendata.util.ObjectUtil.createMap;

/**
 *
 * @author Diavgeia Developers
 */
public class PublishDecisionWithAttachment {
    
    private static final String PDF_FILE_PATH = "/gr/gov/diavgeia/opendata/samples/decisions/PublishDecision.pdf";
    
    private static final String ATTACHMENT1_FILE_PATH = "/gr/gov/diavgeia/opendata/samples/decisions/Attachment.docx";
    
    private static final String ATTACHMENT2_FILE_PATH = "/gr/gov/diavgeia/opendata/samples/decisions/Attachment.xlsx";
    
    private static DecisionStoreRequest createDecisionStoreRequest() {
        DecisionStoreRequest decision = new DecisionStoreRequest();
        
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        
        String entryNumber = Long.toString(nowMillis % 10000);
        
        decision.setSubject("ΑΠΟΦΑΣΗ ΑΝΑΛΗΨΗΣ ΥΠΟΧΡΕΩΣΗΣ " + entryNumber);
        decision.setProtocolNumber("2014/" + System.currentTimeMillis());
        decision.setIssueDate(now);
        
        decision.setDecisionTypeId("Β.1.3"); // ΑΝΑΛΗΨΗ ΥΠΟΧΡΕΩΣΗΣ
        decision.setThematicCategoryIds(asList("20")); // ΟΙΚΟΝΟΜΙΚΕΣ ΚΑΙ ΕΜΠΟΡΙΚΕΣ ΣΥΝΑΛΛΑΓΕΣ
        
        decision.setOrganizationId("30");
        decision.setUnitIds(asList("10256"));
        decision.setSignerIds(asList("10352"));
        
        decision.setExtraFieldValues(createMap(
            "financialYear", 2014,
            "budgettype", "Τακτικός Προϋπολογισμός",
            "entryNumber", entryNumber,
            "partialead", false,
            "recalledExpenseDecision", false,
            "amountWithVAT", createMap(
                "amount", 150,
                "currency", "EUR"
            ),
            "amountWithKae", asList(
                createMap("kae", "1234", "amountWithVAT", 100),
                createMap("kae", "4321", "amountWithVAT", 50)
            ),
            "documentType", "ΠΡΑΞΗ"
        ));
        
        // Set this to false to temporarily store the decision (No ADA is created).
        decision.setPublish(true);
        
        decision.setAttachments(null);
        
        return decision;
    }
    
    private static void addAttachments(MultipartPostHttpRequestBuilder post) throws IOException {
        List<String> attachmentDescriptions = new ArrayList<>();
        
        // Attachment.docx
        byte[] att1Bytes = null;
        try (InputStream attStream = PublishDecisionWithJsonString.class.getResourceAsStream(ATTACHMENT1_FILE_PATH)) {
            att1Bytes = IOUtils.toByteArray(attStream);
            attachmentDescriptions.add("The first attachment is a Word file");
            // Add the rest of the attachment information in the binary part
            post.addFilePart("attachments", "Attachment.docx", att1Bytes, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
        
        // Attachment.xlsx
        byte[] att2Bytes = null;
        try (InputStream attStream = PublishDecisionWithJsonString.class.getResourceAsStream(ATTACHMENT2_FILE_PATH)) {
            att2Bytes = IOUtils.toByteArray(attStream);
            attachmentDescriptions.add("The second attachment is an Excel file");
            // Add the rest of the attachment information in the binary part
            post.addFilePart("attachments", "Attachment.xlsx", att2Bytes, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        }
        
        post.addFormField("attachmentDescr", JsonUtil.toString(attachmentDescriptions));
    }
    
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        // Prepare metadata and decision file
        DecisionStoreRequest decision = createDecisionStoreRequest();
        byte[] pdfContent;
        try (InputStream pdfStream = PublishDecisionWithJsonString.class.getResourceAsStream(PDF_FILE_PATH)) {
            pdfContent = IOUtils.toByteArray(pdfStream);
        }

        // Prepare POST
        MultipartPostHttpRequestBuilder post = HttpRequests.postMultipart(conf.getBaseUrl() + "/decisions");
        if (conf.isAuthenticationEnabled()) {
            post.addCredentials(conf.getUsername(), conf.getPassword());
        }
        // post.addCredentials("30_test_api_user", "ApiUser30@Test");
        post.addHeader("Accept", "application/json");

        String jsonString = JsonUtil.toString(decision);
        post.addFormField("metadata", jsonString);
        post.addFilePart("decisionFile", "decision.pdf", pdfContent, "application/pdf");
        
        addAttachments(post);

        HttpResponse response = post.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String responseBody = StringUtil.readInputStream(response.getBody());
            System.out.println(responseBody);
            Decision d = JsonUtil.fromString(responseBody, Decision.class);
            System.out.println("Got ADA: " + d.getAda());
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
            if (response.getStatusCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
                String errorBody = StringUtil.readInputStream(response.getBody());
                Errors errors = JsonUtil.fromString(errorBody, Errors.class);
                for (Error err : errors.getErrors()) {
                    System.out.println(String.format("%s: %s", err.getErrorCode(), err.getErrorMessage()));
                }
            }
        }
    }
}
