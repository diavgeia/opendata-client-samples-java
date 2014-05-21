package gr.gov.diavgeia.opendata.samples.orgstructure.org;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.xml.Organization;
import gr.gov.diavgeia.opendata.xml.OrganizationDetails;
import gr.gov.diavgeia.opendata.xml.OrganizationDetails.SupervisedOrganizations;
import gr.gov.diavgeia.opendata.xml.Signer;
import gr.gov.diavgeia.opendata.xml.Signers;
import gr.gov.diavgeia.opendata.xml.Unit;
import gr.gov.diavgeia.opendata.xml.Units;
import gr.gov.diavgeia.opendata.xml.Position;
import gr.gov.diavgeia.opendata.xml.Positions;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.XmlUtil;

/**
 *
 * @author Diavgeia Developers
 */
public class GetOrganizationDetailsXml {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/organizations/30/details");
        req.addHeader("Accept", "application/xml");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            OrganizationDetails org = XmlUtil.fromString(body, OrganizationDetails.class);            
            System.out.println(String.format("%s: %s (Latin name: %s)", org.getUid(), 
                org.getLabel(), org.getLatinName()));
            
            System.out.println("\nOrganization units:");
            Units units = org.getUnits();
            for (Unit unit : units.getUnit()) {
                System.out.println(String.format("%s: %s", unit.getUid(), unit.getLabel()));
            }
            
            System.out.println("\nOrganization signers:");
            Signers signers = org.getSigners();
            for (Signer signer: signers.getSigner()) {
                System.out.println(String.format("%s: %s %s, %s", signer.getUid(),
                    signer.getLastName(), signer.getFirstName(), signer.getPositionLabel()));
            }
            
            System.out.println("\nOrganization positions:");
            Positions positions = org.getPositions();
            for (Position pos : positions.getPosition()) {
                System.out.println(String.format("%s: %s", pos.getUid(), pos.getLabel()));
            }
            
            System.out.println("\nSupervised organizations:");
            SupervisedOrganizations subs = org.getSupervisedOrganizations();
            for (Organization sub: subs.getOrganization()) {
                System.out.println(String.format("%s: %s (Latin name: %s)", 
                    sub.getUid(), sub.getLabel(), sub.getLatinName()));
            }
            
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
