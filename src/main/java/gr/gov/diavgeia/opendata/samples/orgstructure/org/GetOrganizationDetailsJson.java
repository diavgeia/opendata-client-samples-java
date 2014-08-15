package gr.gov.diavgeia.opendata.samples.orgstructure.org;

import java.util.List;
import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.json.Organization;
import gr.gov.diavgeia.opendata.json.OrganizationDetails;
import gr.gov.diavgeia.opendata.json.Signer;
import gr.gov.diavgeia.opendata.json.Signer.SignerUnit;
import gr.gov.diavgeia.opendata.json.Unit;
import gr.gov.diavgeia.opendata.json.Position;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.JsonUtil;

/**
 *
 * @author Diavgeia Developers
 */
public class GetOrganizationDetailsJson {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/organizations/30/details");
        req.addHeader("Accept", "application/json");

        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            OrganizationDetails org = JsonUtil.fromString(body, OrganizationDetails.class);
            System.out.println(String.format("%s: %s (Latin name: %s)", org.getUid(),
                    org.getLabel(), org.getLatinName()));

            System.out.println("\nOrganization units:");
            for (Unit unit : org.getUnits()) {
                System.out.println(String.format("%s: %s", unit.getUid(), unit.getLabel()));
            }

            System.out.println("\nOrganization signers:");
            for (Signer signer : org.getSigners()) {
                System.out.println(String.format("%s: %s %s", signer.getUid(),
                        signer.getLastName(), signer.getFirstName()));
                List<SignerUnit> signerUnits = signer.getUnits();
                if (signerUnits != null) {
                    for (SignerUnit unit : signerUnits) {
                        System.out.println(String.format("Unit ID: %s, Position in unit: (%s, %s)", unit.getUid(), unit.getPositionId(), unit.getPositionLabel()));
                    }
                }
            }

            System.out.println("\nOrganization positions:");
            for (Position pos : org.getPositions()) {
                System.out.println(String.format("%s: %s", pos.getUid(), pos.getLabel()));
            }

            System.out.println("\nSupervised organizations:");
            for (Organization sub : org.getSupervisedOrganizations()) {
                System.out.println(String.format("%s: %s (Latin name: %s)",
                        sub.getUid(), sub.getLabel(), sub.getLatinName()));
            }

        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
