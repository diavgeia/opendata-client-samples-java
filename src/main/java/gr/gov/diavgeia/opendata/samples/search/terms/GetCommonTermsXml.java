package gr.gov.diavgeia.opendata.samples.search.terms;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.xml.SearchTerm;
import gr.gov.diavgeia.opendata.xml.SearchTerms;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.XmlUtil;

/**
 *
 * @author Diavgeia Developers
 */
public class GetCommonTermsXml {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/search/terms/common");
        req.addHeader("Accept", "application/xml");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            SearchTerms terms = XmlUtil.fromString(body, SearchTerms.class);
            
            for (SearchTerm term : terms.getSearchTerm()) {
                System.out.println(String.format("%s: %s", term.getTerm(), term.getLabel()));
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
