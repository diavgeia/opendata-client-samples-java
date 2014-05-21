package gr.gov.diavgeia.opendata.samples;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * 
 * @author Diavgeia Developers
 */
public class Configuration {
    private static final String PROPERTIES_PREFIX = "opendata";
    private static final Logger LOG = Logger.getLogger(Configuration.class.getName());
    
    private static final String PROP_BASE_URL = "root";
    private static final String PROP_AUTH = "auth";
    private static final String PROP_USERNAME = "auth.username";
    private static final String PROP_PASSWORD = "auth.password";
    
    public static final String PROP_BASE_URL_DEFAULT = "https://test3.diavgeia.gov.gr/luminapi/opendata";
    public static final String PROP_AUTH_DEFAULT = "false";
    
    private String baseUrl;
    private boolean auth;
    private String username;
    private String password;
    
    public Configuration() {
        this(System.getProperties());
    }
    
    public Configuration(final Properties props) {
        initializeConfiguration(props);
    }
    
    public Configuration(final String filePath) {
        this(new File(filePath));
    }
    
    public Configuration(final File file) {
        
        Properties p = new Properties();
        if (file != null) {
            try {
                FileReader f = new FileReader(file);
                p.load(f);
            } catch (IOException e) {
                throw new IllegalStateException("Error while loading configuration from " + file.getName());
            }
        }
        initializeConfiguration(p);
    }
    
    public String getBaseUrl() {
        return baseUrl;
    }
    
    public boolean isAuthenticationEnabled() {
        return auth;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    // PRIVATE ////////////////////
    
    private String getFullPropertyName(String toolProperty) {
        return PROPERTIES_PREFIX + "." + toolProperty;
    }
    
    private String getPropertyValue(final Properties props, final String toolProperty, final String defaultValue) {
        String propValue = props.getProperty(getFullPropertyName(toolProperty));
        if (propValue == null) {
            propValue = defaultValue;
        }
        return propValue;
    }
    
    private void initializeConfiguration(final Properties props) {
        System.out.println("All Props:");
        System.out.println(props);
        System.out.println("\n\n");
        logProperties(props, new String[] { PROP_BASE_URL, PROP_AUTH, PROP_USERNAME, PROP_PASSWORD });
        String propValue = getPropertyValue(props, PROP_BASE_URL, PROP_BASE_URL_DEFAULT);
        this.baseUrl = propValue;
        
        propValue = getPropertyValue(props, PROP_AUTH, PROP_AUTH_DEFAULT);
        this.auth = ("true".equals(propValue));
        
        if (this.auth) {
            this.username = getPropertyValue(props, PROP_USERNAME, null);
            this.password = getPropertyValue(props, PROP_PASSWORD, null);
        }
    }
    
    private void logProperties(Properties props, String[] propNames) {
        for (String propName : propNames) {
            String key = PROPERTIES_PREFIX + "." + propName;
            String val = props.getProperty(key);
            if (val != null) {
                LOG.log(Level.INFO, "{0}: {1}", new Object[] { key, val });
            }
        }
    }
}
