package org.example.models;

import java.util.List;

public class ReportSearchResult {
    public String search;
    public List<String> result;
    public long time;

    public ReportSearchResult(String search, List<String> result, long time) {
        this.search = search;
        this.result = result;
        this.time = time;
    }

    public String toString(){
        return "Запрос "+ search + "\nРезультат "+result.toString() + "\n";
    }
}
