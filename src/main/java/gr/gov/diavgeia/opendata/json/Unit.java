package gr.gov.diavgeia.opendata.json;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Diavgeia Developers
 */
public class Unit {
    

    private String uid;

    private String label;

    private String abbreviation;
    
    private boolean active;
    
    private Date activeFrom;
    
    private Date activeUntil;

    private String category;

    private List<String> unitDomains;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getUnitDomains() {
        return unitDomains;
    }

    public void setUnitDomains(List<String> unitDomains) {
        this.unitDomains = unitDomains;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Date getActiveUntil() {
        return activeUntil;
    }

    public void setActiveUntil(Date activeUntil) {
        this.activeUntil = activeUntil;
    }
}
