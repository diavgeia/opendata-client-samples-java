package gr.gov.diavgeia.opendata.samples.search;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.json.Decision;
import gr.gov.diavgeia.opendata.json.DecisionSearchResult;
import gr.gov.diavgeia.opendata.json.Info;
import gr.gov.diavgeia.opendata.util.JsonUtil;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.samples.Formatters;

/**
 * 
 * @author Diavgeia Developers
 */
public class SimpleSearchJson {
    
    /**
     * Get decision and print it
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/search?from_date=2014-01-01");
        if (conf.isAuthenticationEnabled()) {
            req.addCredentials(conf.getUsername(), conf.getPassword());
        }
        
        req.addHeader("Accept", "application/json");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            
            DecisionSearchResult searchResults = JsonUtil.fromString(body, DecisionSearchResult.class);
            
            Info info = searchResults.getInfo();
            
            System.out.println(String.format("Retrieved %d decisions", info.getActualSize()));
            System.out.println(String.format("Total matching decisions: %d", info.getTotal()));
            System.out.println(String.format("Search query syntax", info.getQuery()));
            
            for (Decision decision : searchResults.getDecisions()) {
                System.out.println(String.format(
                        "ΑΔΑ: %s, Αρ. Πρωτ.: %s, Ημ/νία έκδοσης: %s, Ημ/νία και ώρα ανάρτησης: %s, Θέμα: %s",
                        decision.getAda(), decision.getProtocolNumber(),
                        Formatters.DATE_FORMAT.format(decision.getIssueDate()),
                        Formatters.TIMESTAMP_FORMAT.format(decision.getSubmissionTimestamp()), decision.getSubject()));
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
