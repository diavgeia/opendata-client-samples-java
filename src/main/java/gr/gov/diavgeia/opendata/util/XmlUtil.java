package gr.gov.diavgeia.opendata.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import gr.gov.diavgeia.opendata.xml.Decision;

/**
 * Utility methods for XML conversions.
 * 
 * @author Diavgeia Developers
 */
public class XmlUtil {
    
    private static final JAXBContext JAXB_CTX;
    
    static {
        try {
            JAXB_CTX = JAXBContext.newInstance(Decision.class.getPackage().getName());
        } catch (JAXBException e) {
            throw new RuntimeException("Error while initializing JAXB context", e);
        }
    }
    
    /**
     * Converts a string to an object of the specified type.
     * 
     * @param xmlStr
     * @param cls
     * @return
     */
    public static <T> T fromString(String xmlStr, Class<T> cls) {
        try {
            Unmarshaller um = JAXB_CTX.createUnmarshaller();
            StringReader reader = new StringReader(xmlStr);
            Source source = new StreamSource(reader);
            JAXBElement<T> elem = um.unmarshal(source, cls);
            return elem.getValue();
        } catch (JAXBException e) {
            throw new RuntimeException("Error while unmarshalling decision", e);
        }
    }
}
