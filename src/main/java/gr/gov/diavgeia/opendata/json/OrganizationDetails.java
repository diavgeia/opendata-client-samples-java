package gr.gov.diavgeia.opendata.json;

import java.util.List;

/**
 *
 * @author Diavgeia Developers
 */
public class OrganizationDetails extends Organization {
    private List<Unit> units;
    private List<Signer> signers;
    private List<Position> positions;
    private List<Organization> supervisedOrganizations;

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public List<Signer> getSigners() {
        return signers;
    }

    public void setSigners(List<Signer> signers) {
        this.signers = signers;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public List<Organization> getSupervisedOrganizations() {
        return supervisedOrganizations;
    }

    public void setSupervisedOrganizations(List<Organization> supervisedOrganizations) {
        this.supervisedOrganizations = supervisedOrganizations;
    }
}
