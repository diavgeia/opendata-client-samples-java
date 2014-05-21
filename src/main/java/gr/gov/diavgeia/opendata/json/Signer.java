package gr.gov.diavgeia.opendata.json;

import java.util.List;

/**
 *
 * @author Diavgeia Developers
 */
public class Signer {
    private String uid;

    private String firstName;

    private String lastName;

    private boolean active;

    private String organizationId;

    private String positionId;

    private String positionLabel;

    private boolean hasOrganizationSignRights;

    private List<String> unitIds;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionLabel() {
        return positionLabel;
    }

    public void setPositionLabel(String positionLabel) {
        this.positionLabel = positionLabel;
    }

    public boolean isHasOrganizationSignRights() {
        return hasOrganizationSignRights;
    }

    public void setHasOrganizationSignRights(boolean hasOrganizationSignRights) {
        this.hasOrganizationSignRights = hasOrganizationSignRights;
    }

    public List<String> getUnitIds() {
        return unitIds;
    }

    public void setUnitIds(List<String> unitIds) {
        this.unitIds = unitIds;
    }
}
