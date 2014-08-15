package gr.gov.diavgeia.opendata.samples.decisions;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;

import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.samples.Formatters;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.JsonUtil;
import gr.gov.diavgeia.opendata.json.DecisionVersionLog;
import gr.gov.diavgeia.opendata.json.DecisionVersionLogEntry;

/**
 *
 * @author Diavgeia Developers
 */
public class GetDecisionHistoryJson {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/decisions/ΒΙΞΞΩ6Μ-ΕΕΜ/versionlog");
        req.addHeader("Accept", "application/json");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            System.out.println(body);
            DecisionVersionLog versionLog = JsonUtil.fromString(body, DecisionVersionLog.class);
            System.out.println("ΑΔΑ: " + versionLog.getAda());
            for (DecisionVersionLogEntry entry : versionLog.getVersions()) {
                System.out.println(String.format("Version: %s, Status: %s, Timestamp: %s", entry.getVersionId(), entry.getStatus(), 
                    Formatters.TIMESTAMP_FORMAT.format(entry.getVersionTimestamp())));
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
