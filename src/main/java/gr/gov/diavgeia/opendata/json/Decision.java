package gr.gov.diavgeia.opendata.json;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Diavgeia Developers
 */
public class Decision {
    private String protocolNumber;
    private String subject;
    private Date issueDate;
    private String organizationId;
    private List<String> signerIds;
    private List<String> unitIds;
    private String decisionTypeId;
    private List<String> thematicCategoryIds;
    private Map<String, ? extends Object> extraFieldValues;
    private boolean privateData;
    private String ada;
    private Date submissionTimestamp;
    private String versionId;
    private String status;
    private String url;
    private String documentUrl;
    private String documentChecksum;
    private String correctedVersionId;
    private List<Attachment> attachments;
    
    public String getProtocolNumber() {
        return protocolNumber;
    }
    
    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public Date getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    
    public String getOrganizationId() {
        return organizationId;
    }
    
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    
    public List<String> getSignerIds() {
        return signerIds;
    }
    
    public void setSignerIds(List<String> signerIds) {
        this.signerIds = signerIds;
    }
    
    public List<String> getUnitIds() {
        return unitIds;
    }
    
    public void setUnitIds(List<String> unitIds) {
        this.unitIds = unitIds;
    }
    
    public String getDecisionTypeId() {
        return decisionTypeId;
    }
    
    public void setDecisionTypeId(String decisionTypeId) {
        this.decisionTypeId = decisionTypeId;
    }
    
    public List<String> getThematicCategoryIds() {
        return thematicCategoryIds;
    }
    
    public void setThematicCategoryIds(List<String> thematicCategoryIds) {
        this.thematicCategoryIds = thematicCategoryIds;
    }
    
    public Map<String, ? extends Object> getExtraFieldValues() {
        return extraFieldValues;
    }
    
    public void setExtraFieldValues(Map<String, ? extends Object> extraFieldValues) {
        this.extraFieldValues = extraFieldValues;
    }
    
    public boolean isPrivateData() {
        return privateData;
    }
    
    public void setPrivateData(boolean privateData) {
        this.privateData = privateData;
    }
    
    public String getAda() {
        return ada;
    }
    
    public void setAda(String ada) {
        this.ada = ada;
    }
    
    public Date getSubmissionTimestamp() {
        return submissionTimestamp;
    }
    
    public void setSubmissionTimestamp(Date submissionTimestamp) {
        this.submissionTimestamp = submissionTimestamp;
    }
    
    public String getVersionId() {
        return versionId;
    }
    
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getDocumentUrl() {
        return documentUrl;
    }
    
    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }
    
    public String getDocumentChecksum() {
        return documentChecksum;
    }
    
    public void setDocumentChecksum(String documentChecksum) {
        this.documentChecksum = documentChecksum;
    }
    
    public List<Attachment> getAttachments() {
        return attachments;
    }
    
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getCorrectedVersionId() {
        return correctedVersionId;
    }

    public void setCorrectedVersionId(String correctedVersionId) {
        this.correctedVersionId = correctedVersionId;
    }
}
