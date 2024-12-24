package org.example.models;
public class Report {
    String guid;
    String codeName;
    String description;

    public Report(String guid, String codeName, String description) {
        this.guid = guid;
        this.codeName = codeName;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getGuid() {
        return guid;
    }
}
