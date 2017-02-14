package org.wso2.carbon.sample;

public class SingleUser {

    public Names getName() {
        return name;
    }

    public void setName(Names name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Names {
        public Names(String familyName, String givenName) {
            this.familyName = familyName;
            this.givenName = givenName;
        }

        private String familyName;
        private String givenName;
    }

    private Names name;
    private String userName;
    private String password;
}
