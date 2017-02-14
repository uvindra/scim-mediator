package org.wso2.carbon.sample;

public class UserData {
    private String[] schemas = {Constants.SCIM_SCHEMA};
    private String path = Constants.USERS_PATH;
    private String userName;
    private String method;
    private Object[] emails;
    private Object[] phoneNumbers;
    private String displayName;
    private String externalId;
    private String password;
    private String preferredLanguage;
    private String bulkId;
    private Object[] groups;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getEmails() {
        return emails;
    }

    public void setEmails(Object[] emails) {
        this.emails = emails;
    }

    public Object[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Object[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getBulkId() {
        return bulkId;
    }

    public void setBulkId(String bulkId) {
        this.bulkId = bulkId;
    }

    public Object[] getGroups() {
        return groups;
    }

    public void setGroups(Object[] groups) {
        this.groups = groups;
    }
}
