package gr.gov.diavgeia.opendata.json;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author Diavgeia Developers
 */
public class AttachmentStoreRequest {
    
    private List<Attachment> attach = new ArrayList<>();
    
    private List<String> remove = new ArrayList<>(); // List of attachment IDs
    
    public List<Attachment> getAttach() {
        return attach;
    }
    
    public void setAttach(List<Attachment> attach) {
        this.attach = attach;
    }
    
    public List<String> getRemove() {
        return remove;
    }
    
    public void setRemove(List<String> remove) {
        this.remove = remove;
    }
}
