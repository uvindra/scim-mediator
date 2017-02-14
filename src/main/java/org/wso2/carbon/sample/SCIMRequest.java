package org.wso2.carbon.sample;

import java.util.Vector;

public class SCIMRequest {
    private int failOnErrors = 2;
    private String[] schemas = {Constants.SCIM_SCHEMA};
    private Vector<Object> Operations = new Vector<>();

    public Object[] getOperations() {
        return Operations.toArray();
    }

    public void addOperations(Object operation) {
        Operations.add(operation);
    }

    public int getFailOnErrors() {
        return failOnErrors;
    }

    public void setFailOnErrors(int failOnErrors) {
        this.failOnErrors = failOnErrors;
    }

    public String[] getSchemas() {
        return schemas;
    }

    public void setSchemas(String[] schemas) {
        this.schemas = schemas;
    }

}
