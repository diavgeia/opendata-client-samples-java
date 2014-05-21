package gr.gov.diavgeia.opendata.samples.dictionaries;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.xml.DictionaryItem;
import gr.gov.diavgeia.opendata.xml.DictionaryItems;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.XmlUtil;

public class GetDictionaryItemsXml {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/dictionaries/ORG_CATEGORY");
        req.addHeader("Accept", "application/xml");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            DictionaryItems items = XmlUtil.fromString(body, DictionaryItems.class);
            System.out.println("Dictionary: " + items.getName());
            for (DictionaryItem item : items.getItem()) {
                System.out.println(String.format("%s: %s", item.getUid(), item.getLabel()));
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
