package gr.gov.diavgeia.opendata.json;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Diavgeia Developers
 */
public class DecisionStoreRequest {
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
    private boolean publish;
    private boolean correctedCopy;
    private String documentChecksum;
    private boolean removeDecisionDocument;
    private AttachmentStoreRequest attachments;
    private List<DecisionStoreRequestAction> actions;
    
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
    
    public boolean isPublish() {
        return publish;
    }
    
    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public boolean isCorrectedCopy() {
        return correctedCopy;
    }

    public void setCorrectedCopy(boolean correctedCopy) {
        this.correctedCopy = correctedCopy;
    }
    
    public String getDocumentChecksum() {
        return documentChecksum;
    }
    
    public void setDocumentChecksum(String documentChecksum) {
        this.documentChecksum = documentChecksum;
    }
    
    public boolean isRemoveDecisionDocument() {
        return removeDecisionDocument;
    }
    
    public void setRemoveDecisionDocument(boolean removeDecisionDocument) {
        this.removeDecisionDocument = removeDecisionDocument;
    }
    
    public AttachmentStoreRequest getAttachments() {
        return attachments;
    }
    
    public void setAttachments(AttachmentStoreRequest attachments) {
        this.attachments = attachments;
    }

    public List<DecisionStoreRequestAction> getActions() {
        return actions;
    }

    public void setActions(List<DecisionStoreRequestAction> actions) {
        this.actions = actions;
    }
}
