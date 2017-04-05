package core.bean;

/**
 * 
 * @author rjaywant
 * 
 */
public class Condition {

    private final String property;
    private final Operation operation;
    private final Object value;

    public Condition(String propName, Operation operation, Object value) {
        this.property = propName;
        this.operation = operation;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public Operation getOperation() {
        return operation;
    }

    public Object getValue() {
        return value;
    }

}