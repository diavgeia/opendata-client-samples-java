package gr.gov.diavgeia.opendata.samples.orgstructure.position;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.xml.Position;
import gr.gov.diavgeia.opendata.xml.Positions;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.XmlUtil;

/**
 *
 * @author Diavgeia Developers
 */
public class GetPositionsXml {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/positions");
        req.addHeader("Accept", "application/xml");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            Positions positions = XmlUtil.fromString(body, Positions.class);
            for (Position pos : positions.getPosition()) {
                System.out.println(String.format("%s: %s", pos.getUid(), pos.getLabel()));
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
