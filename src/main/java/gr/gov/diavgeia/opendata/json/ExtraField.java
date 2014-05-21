package gr.gov.diavgeia.opendata.json;

import java.util.List;

/**
 *
 * @author Diavgeia Developers
 */
public class ExtraField {
    private String uid;
    private String label;
    private String help;
    private String type; // string | number | boolean | object | list? | 
    private String validation; // null | none | dictionary | afm | custom | rel_ada
    private boolean required;
    private boolean multiple;
    private int maxLength;
    private String dictionary;
    private String searchTerm;
    private List<String> relAdaDecisionTypes;
    private Boolean relAdaConstrainedInOrganization;
    private List<ExtraField> nestedFields;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getDictionary() {
        return dictionary;
    }

    public void setDictionary(String dictionary) {
        this.dictionary = dictionary;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<String> getRelAdaDecisionTypes() {
        return relAdaDecisionTypes;
    }

    public void setRelAdaDecisionTypes(List<String> relAdaDecisionTypes) {
        this.relAdaDecisionTypes = relAdaDecisionTypes;
    }

    public Boolean isRelAdaConstrainedInOrganization() {
        return relAdaConstrainedInOrganization;
    }

    public void setRelAdaConstrainedInOrganization(Boolean relAdaConstrainedInOrganization) {
        this.relAdaConstrainedInOrganization = relAdaConstrainedInOrganization;
    }

    public List<ExtraField> getNestedFields() {
        return nestedFields;
    }

    public void setNestedFields(List<ExtraField> nestedFields) {
        this.nestedFields = nestedFields;
    }
}
