package gr.gov.diavgeia.opendata.samples.search;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;

import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.samples.Formatters;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.xml.DecisionsSearchResult;
import gr.gov.diavgeia.opendata.xml.Decision;
import gr.gov.diavgeia.opendata.xml.Info;
import gr.gov.diavgeia.opendata.util.XmlUtil;

/**
 * 
 * @author Diavgeia Developers
 */
public class SimpleSearchXml {
    
    /**
     * Get decision and print it
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/search?org=min-interior&type=Β.1.3");
        if (conf.isAuthenticationEnabled()) {
            req.addCredentials(conf.getUsername(), conf.getPassword());
        }
        req.addHeader("Accept", "application/xml");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            
            DecisionsSearchResult searchResults = XmlUtil.fromString(body, DecisionsSearchResult.class);
            
            Info info = searchResults.getInfo();
            
            System.out.println(String.format("Found %d decisions", info.getTotal()));
            System.out.println(String.format("Search query syntax", info.getQuery()));
            
            for (Decision decision : searchResults.getDecisions().getDecision()) {
                System.out.println(String.format(
                        "ΑΔΑ: %s, Αρ. Πρωτ.: %s, Ημ/νία έκδοσης: %s, Ημ/νία και ώρα ανάρτησης: %s, Θέμα: %s",
                        decision.getAda(), decision.getProtocolNumber(),
                        Formatters.DATE_FORMAT.format(decision.getIssueDate().toGregorianCalendar().getTime()),
                        Formatters.TIMESTAMP_FORMAT.format(decision.getSubmissionTimestamp().toGregorianCalendar().getTime()),
                        decision.getSubject()));
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
