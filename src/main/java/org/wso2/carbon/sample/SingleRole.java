package org.wso2.carbon.sample;

import java.util.Vector;

public class SingleRole {
    private String displayName;
    private Vector<Object> members = new Vector<>();

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Object[] getMembers() {
        return members.toArray();
    }

    public void addMember(Object member) {
        this.members.add(member);
    }
}
