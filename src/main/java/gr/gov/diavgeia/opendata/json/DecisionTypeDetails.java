package gr.gov.diavgeia.opendata.json;

import java.util.List;

/**
 *
 * @author Diavgeia Developers
 */
public class DecisionTypeDetails extends DecisionType {
    private List<ExtraField> extraFields;

    public List<ExtraField> getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(List<ExtraField> extraFields) {
        this.extraFields = extraFields;
    }
}
