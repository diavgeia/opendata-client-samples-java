package gr.gov.diavgeia.opendata.json;

import java.util.List;

/**
 * 
 * @author Diavgeia Developers
 */
public class DecisionSearchResult {
    private List<Decision> decisions;
    private Info info;
    
    public List<Decision> getDecisions() {
        return decisions;
    }
    
    public void setDecisions(List<Decision> decisions) {
        this.decisions = decisions;
    }
    
    public Info getInfo() {
        return info;
    }
    
    public void setInfo(Info info) {
        this.info = info;
    }
}
