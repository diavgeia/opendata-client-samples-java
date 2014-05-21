package gr.gov.diavgeia.opendata.json;

/**
 * 
 * @author Diavgeia Developers
 */
public class Info {
    
    private String query;
    private int page;
    private int size;
    private int actualSize;
    private long total;
    private String order;
    
    public String getQuery() {
        return query;
    }
    
    public void setQuery(String query) {
        this.query = query;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getActualSize() {
        return actualSize;
    }
    
    public void setActualSize(int actualSize) {
        this.actualSize = actualSize;
    }
    
    public long getTotal() {
        return total;
    }
    
    public void setTotal(long total) {
        this.total = total;
    }
    
    public String getOrder() {
        return order;
    }
    
    public void setOrder(String order) {
        this.order = order;
    }
}
