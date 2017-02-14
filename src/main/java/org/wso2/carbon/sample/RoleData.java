package org.wso2.carbon.sample;

public class RoleData {
    private String[] schemas = {Constants.SCIM_SCHEMA};
    private String path = Constants.ROLES_PATH;
    private String method;
    private String displayName;
    private String externalId;
    private String bulkId;
    private Object[] members;

    public String[] getSchemas() {
        return schemas;
    }

    public void setSchemas(String[] schemas) {
        this.schemas = schemas;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getBulkId() {
        return bulkId;
    }

    public void setBulkId(String bulkId) {
        this.bulkId = bulkId;
    }

    public Object[] getMembers() {
        return members;
    }

    public void setMembers(Object[] members) {
        this.members = members;
    }
}
