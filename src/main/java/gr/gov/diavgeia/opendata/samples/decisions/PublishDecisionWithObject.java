package gr.gov.diavgeia.opendata.samples.decisions;

import static java.util.Arrays.asList;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Date;

import org.apache.commons.io.IOUtils;

import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.http.MultipartPostHttpRequestBuilder;
import gr.gov.diavgeia.opendata.json.DecisionStoreRequest;
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
public class PublishDecisionWithObject {

    private static final String PDF_FILE_PATH = "/gr/gov/diavgeia/opendata/samples/decisions/PublishDecision.pdf";

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

        // Set this to true to officialy publish the decision.
        decision.setPublish(true);

        return decision;
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
