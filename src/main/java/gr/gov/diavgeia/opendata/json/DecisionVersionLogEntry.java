package gr.gov.diavgeia.opendata.json;

import java.util.Date;

/**
 *
 * @author Diavgeia Developers
 */
public class DecisionVersionLogEntry {
    private String versionId;
    private String creator;
    private Date versionTimestamp;
    private String description;
    private String status;
    private String correctedVersionId;

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getVersionTimestamp() {
        return versionTimestamp;
    }

    public void setVersionTimestamp(Date versionTimestamp) {
        this.versionTimestamp = versionTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCorrectedVersionId() {
        return correctedVersionId;
    }

    public void setCorrectedVersionId(String correctedVersionId) {
        this.correctedVersionId = correctedVersionId;
    }
}
