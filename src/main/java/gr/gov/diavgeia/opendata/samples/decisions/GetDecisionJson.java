package gr.gov.diavgeia.opendata.samples.decisions;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;

import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.json.Decision;
import gr.gov.diavgeia.opendata.samples.Formatters;
import gr.gov.diavgeia.opendata.util.JsonUtil;
import java.net.HttpURLConnection;

/**
 * 
 * @author Diavgeia Developers
 */
public class GetDecisionJson {
    
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/decisions/Ω82ΦΝ-Ρ3Ε");
        req.addHeader("Accept", "application/json");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            System.out.println(body);
            Decision decision = JsonUtil.fromString(body, Decision.class);
            System.out.println(String.format(
                    "ΑΔΑ: %s, Αρ. Πρωτ.: %s, Ημ/νία έκδοσης: %s, Ημ/νία και ώρα ανάρτησης: %s, Θέμα: %s",
                    decision.getAda(), decision.getProtocolNumber(),
                    Formatters.DATE_FORMAT.format(decision.getIssueDate()),
                    Formatters.TIMESTAMP_FORMAT.format(decision.getSubmissionTimestamp()), decision.getSubject()));
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
