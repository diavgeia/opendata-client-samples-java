package gr.gov.diavgeia.opendata.samples.orgstructure.signer;

import java.net.HttpURLConnection;
import java.util.List;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.xml.Signer;
import gr.gov.diavgeia.opendata.xml.SignerUnitType;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.XmlUtil;

/**
 *
 * @author Diavgeia Developers
 */
public class GetSignerXml {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/signers/10342");
        req.addHeader("Accept", "application/xml");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            Signer signer = XmlUtil.fromString(body, Signer.class);
            System.out.println(String.format("%s: %s %s", signer.getUid(), signer.getLastName(), signer.getFirstName()));
            List<SignerUnitType> signerUnits = signer.getUnits().getUnit();
            if (signerUnits != null) {
                for (SignerUnitType unit : signerUnits) {
                    System.out.println(String.format("Unit ID: %s, Position: (%s, %s)", unit.getUid(), unit.getPositionId(), unit.getPositionLabel()));
                }
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
