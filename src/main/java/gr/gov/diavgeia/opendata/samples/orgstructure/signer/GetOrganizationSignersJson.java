package gr.gov.diavgeia.opendata.samples.orgstructure.signer;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.json.Signer;
import gr.gov.diavgeia.opendata.json.Signers;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.JsonUtil;

/**
 *
 * @author Diavgeia Developers
 */
public class GetOrganizationSignersJson {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/organizations/30/signers");
        req.addHeader("Accept", "application/json");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            Signers signers = JsonUtil.fromString(body, Signers.class);
            
            for (Signer signer : signers.getSigners()) {
                System.out.println(String.format("%s: %s %s, %s", signer.getUid(), 
                    signer.getLastName(), signer.getFirstName(), signer.getPositionLabel()));
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
