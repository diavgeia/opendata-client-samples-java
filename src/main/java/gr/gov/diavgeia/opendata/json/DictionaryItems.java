package gr.gov.diavgeia.opendata.json;

import java.util.List;

public class DictionaryItems {
    private String name;
    private List<DictionaryItem> items;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<DictionaryItem> getItems() {
        return items;
    }
    
    public void setItems(List<DictionaryItem> items) {
        this.items = items;
    }
}
