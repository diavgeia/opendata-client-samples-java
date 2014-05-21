package gr.gov.diavgeia.opendata.util;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Utility methods for string manipulation.
 * 
 * @author Diavgeia Developers
 */
public class StringUtil {
    /**
     * Concatenates the String representations of the specified objects using the {@code delimiter}.
     * 
     * @param delimiter
     * @param objects
     * @return Non-null String if delimiter and objects are not null, null if the delimiter is not null but no input
     *         objects are provided.
     * @throws NullPointerException if any of the arguments is null
     */
    public static String join(String delimiter, Object... objects) {
        if (delimiter == null) {
            throw new NullPointerException();
        }
        String joined = null;
        if (objects != null && objects.length > 0) {
            StringBuilder str = new StringBuilder(objects[0].toString());
            for (int i = 1; i < objects.length; i++) {
                str.append(delimiter).append(objects[i].toString());
            }
            joined = str.toString();
        }
        return joined;
    }
    
    /**
     * Concatenates the String representations of the specified objects using the {@code delimiter}.
     * 
     * @param delimiter
     * @param objects
     * @return Non-null String if delimiter and objects are not null, null if the delimiter is not null but the
     *         collection of input objects is empty.
     * @throws NullPointerException if any of the arguments is null
     */
    public static String join(String delimiter, Collection<?> objects) {
        if (delimiter == null) {
            throw new NullPointerException();
        }
        String joined = null;
        if (objects != null && objects.size() > 0) {
            Iterator<?> iter = objects.iterator();
            StringBuilder str = new StringBuilder(iter.next().toString());
            while (iter.hasNext()) {
                str.append(delimiter).append(iter.next().toString());
            }
            joined = str.toString();
        }
        return joined;
    }
    
    /**
     * Reads the contents of the specified stream and returns them as a string
     * of the specified encoding. It then closes the stream.
     * 
     * @param stream
     * @param encoding
     * @return
     * @throws IOException 
     */
    public static String readInputStream(InputStream stream, String encoding) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(stream, encoding));
        StringBuilder str = new StringBuilder();
        
        String line;
        while ((line = in.readLine()) != null) {
            str.append(line);
        }
        in.close();
        return str.toString();
    }
    
    /**
     * Reads the contents of the specified stream and returns them as a 
     * UTF-8-encoded string. It then closes the stream.
     * 
     * @param stream
     * @return
     * @throws IOException 
     */
    public static String readInputStream(InputStream stream) throws IOException {
        return readInputStream(stream, "UTF-8");
    }
}
