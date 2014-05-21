package gr.gov.diavgeia.opendata.samples.dictionaries;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.xml.Dictionaries;
import gr.gov.diavgeia.opendata.xml.Dictionary;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.XmlUtil;

public class GetDictionariesXml {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/dictionaries");
        req.addHeader("Accept", "application/xml");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            Dictionaries dicts = XmlUtil.fromString(body, Dictionaries.class);
            
            for (Dictionary dict : dicts.getDictionary()) {
                System.out.println(String.format("%s: %s", dict.getUid(), dict.getLabel()));
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
