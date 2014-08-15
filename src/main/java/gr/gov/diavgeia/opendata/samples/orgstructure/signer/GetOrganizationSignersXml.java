package gr.gov.diavgeia.opendata.samples.orgstructure.signer;

import java.net.HttpURLConnection;
import java.util.List;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.xml.Signers;
import gr.gov.diavgeia.opendata.xml.Signer;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.XmlUtil;
import gr.gov.diavgeia.opendata.xml.SignerUnitType;

/**
 *
 * @author Diavgeia Developers
 */
public class GetOrganizationSignersXml {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/organizations/30/signers");
        req.addHeader("Accept", "application/xml");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            Signers signers = XmlUtil.fromString(body, Signers.class);
            for (Signer signer : signers.getSigner()) {
                System.out.println(String.format("%s: %s %s", signer.getUid(), 
                    signer.getLastName(), signer.getFirstName()));
                List<SignerUnitType> signetUnits = signer.getUnits().getUnit();
                for (SignerUnitType unit : signetUnits) {
                    System.out.println(String.format("Unit ID: %s, Position in unit: (%s, %s)", unit.getUid(), unit.getPositionId(), unit.getPositionLabel()));
                }
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
