package gr.gov.diavgeia.opendata.json;

import java.util.List;

/**
 *
 * @author Diavgeia Developers
 */
public class DecisionVersionLog {
    private List<DecisionVersionLogEntry> versions;
    private String ada;
    private String organizationId;

    public List<DecisionVersionLogEntry> getVersions() {
        return versions;
    }

    public void setVersions(List<DecisionVersionLogEntry> versions) {
        this.versions = versions;
    }

    public String getAda() {
        return ada;
    }

    public void setAda(String ada) {
        this.ada = ada;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}
