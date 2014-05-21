package gr.gov.diavgeia.opendata.http;

/**
 *
 * @author Diavgeia Developers
 */
public class FilePart {
        
        private String filename;
        private byte[] content;
        private String contentType;
        
        public FilePart() {
        }
        
        public FilePart(String filename, byte[] content, String contentType) {
            this.filename = filename;
            this.content = content;
            this.contentType = contentType;
        }
        
        public String getFilename() {
            return filename;
        }
        
        public void setFilename(String filename) {
            this.filename = filename;
        }
        
        public byte[] getContent() {
            return content;
        }
        
        public void setContent(byte[] content) {
            this.content = content;
        }
        
        public String getContentType() {
            return contentType;
        }
        
        public void setContentType(String contentType) {
            this.contentType = contentType;
        }
        
        @Override
        public String toString() {
            return "[" + filename + ", " + contentType + ", " + content.length + " bytes]";
        }

}
