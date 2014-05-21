package gr.gov.diavgeia.opendata.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility methods for object construction.
 *
 * @author Diavgeia Developers
 */
public class ObjectUtil {
    
    /**
     * Builds a {@link Map} from the specified arguments, using the odd arguments
     * as keys and the arguments that immediately follow them as values.
     * 
     * <p>For example, {@code createMap("key1", "value1", "key2", "value2")}
     * creates {@code { "key1" => "value1", "key2" => "value2" }}.
     * </p>
     * 
     * @param args
     * @return 
     */
    public static Map createMap(Object ... args) {
        Map params = new HashMap();
        int len = (args.length % 2 == 0? args.length : (args.length - 1));
        for (int i = 0; i < len; i += 2) {
            params.put(args[i], args[i + 1]);
        }
        return params;
    }
}
