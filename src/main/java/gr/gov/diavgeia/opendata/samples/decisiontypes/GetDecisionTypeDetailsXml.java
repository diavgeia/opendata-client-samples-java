package gr.gov.diavgeia.opendata.samples.decisiontypes;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.xml.DecisionTypeDetails;
import gr.gov.diavgeia.opendata.xml.ExtraField;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.XmlUtil;

/**
 *
 * @author Diavgeia Developers
 */
public class GetDecisionTypeDetailsXml {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/types/Β.1.3/details");
        req.addHeader("Accept", "application/xml");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            DecisionTypeDetails dt = XmlUtil.fromString(body, DecisionTypeDetails.class);
            System.out.println(String.format("%s: %s", dt.getUid(), dt.getLabel()));
            for (ExtraField ef : dt.getExtraFields().getExtraField()) {
                System.out.println(String.format("%s: %s", ef.getUid(), ef.getLabel()));
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
