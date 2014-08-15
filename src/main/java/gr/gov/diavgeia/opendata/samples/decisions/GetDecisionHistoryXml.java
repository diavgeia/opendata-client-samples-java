package gr.gov.diavgeia.opendata.samples.decisions;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;

import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.samples.Formatters;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.xml.DecisionVersionLog;
import gr.gov.diavgeia.opendata.xml.DecisionVersionLogEntry;
import gr.gov.diavgeia.opendata.util.XmlUtil;


/**
 *
 * @author Diavgeia Developers
 */
public class GetDecisionHistoryXml {
    
    /**
     * Get decision and print it
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/decisions/ΒΙΞΞΩ6Μ-ΕΕΜ/versionlog");
        req.addHeader("Accept", "application/xml");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            DecisionVersionLog versionLog = XmlUtil.fromString(body, DecisionVersionLog.class);
            System.out.println("ΑΔΑ: " + versionLog.getAda());
            for (DecisionVersionLogEntry entry : versionLog.getVersions().getVersion()) {
                System.out.println(String.format("Version: %s, Status: %s, Timestamp: %s", entry.getVersionId(), entry.getStatus(), 
                    Formatters.TIMESTAMP_FORMAT.format(entry.getVersionTimestamp().toGregorianCalendar().getTime())));
            }
            
            
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
