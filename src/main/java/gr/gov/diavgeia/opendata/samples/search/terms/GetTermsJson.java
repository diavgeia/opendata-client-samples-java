package gr.gov.diavgeia.opendata.samples.search.terms;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.json.Term;
import gr.gov.diavgeia.opendata.json.Terms;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.JsonUtil;


/**
 *
 * @author Diavgeia Developers
 */
public class GetTermsJson {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/search/terms");
        req.addHeader("Accept", "application/json");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            Terms terms = JsonUtil.fromString(body, Terms.class);
            
            for (Term term : terms.getTerms()) {
                System.out.println(String.format("%s: %s", term.getTerm(), term.getLabel()));
            }
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
