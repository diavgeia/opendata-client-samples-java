package gr.gov.diavgeia.opendata.json;

import java.util.List;

/**
 *
 * @author Diavgeia Developers
 */
public class Organization {
    private String uid;

    private String label;

    private String abbreviation;

    private String latinName;

    private String status;

    private String category;

    private String vatNumber;

    private String fekNumber;

    private String fekIssue;

    private String fekYear;

    private String odeManagerEmail;

    private String website;

    private String supervisorId;

    private String supervisorLabel;
    
    private List<String> organizationDomains;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getFekNumber() {
        return fekNumber;
    }

    public void setFekNumber(String fekNumber) {
        this.fekNumber = fekNumber;
    }

    public String getFekIssue() {
        return fekIssue;
    }

    public void setFekIssue(String fekIssue) {
        this.fekIssue = fekIssue;
    }

    public String getFekYear() {
        return fekYear;
    }

    public void setFekYear(String fekYear) {
        this.fekYear = fekYear;
    }

    public String getOdeManagerEmail() {
        return odeManagerEmail;
    }

    public void setOdeManagerEmail(String odeManagerEmail) {
        this.odeManagerEmail = odeManagerEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorLabel() {
        return supervisorLabel;
    }

    public void setSupervisorLabel(String supervisorLabel) {
        this.supervisorLabel = supervisorLabel;
    }

    public List<String> getOrganizationDomains() {
        return organizationDomains;
    }

    public void setOrganizationDomains(List<String> organizationDomains) {
        this.organizationDomains = organizationDomains;
    }
}
