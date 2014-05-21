package gr.gov.diavgeia.opendata.samples;

import java.text.SimpleDateFormat;

/**
 * Common objects for text formatting.
 *
 * @author Diavgeia Developers
 */
public class Formatters {
    
    /** DD/MM/YYYY formatter for dates */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    
    /** DD/MM/YYYY HH:MM:SS formatter for timestamps */
    public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
}
