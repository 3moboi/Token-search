package org.example.search;

import org.example.models.ReportSearchResult;
import org.example.utility.ActionsOnMathematicalSets;
import org.example.models.Report;
import org.example.models.TokenDictionary;
import org.example.utility.TextProcessing;
import org.example.utility.TruthTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportSearcher {
    private ArrayList<Report> reports;
    private Map<String, ArrayList<Integer>> dictionary;
    public ReportSearcher(ArrayList<Report> reports) {
        TokenDictionary dictionary = new TokenDictionary(reports);
        this.reports = reports;
        this.dictionary = dictionary.getDictionary();
    }

    public List<ReportSearchResult> search(List<String> requests){
        List<ReportSearchResult> results = new ArrayList<>();
        for (String request : requests) {
            long startTime = System.currentTimeMillis();

            List<String> matchedGuids = new ArrayList<>();
            for(int reportId: searchByRequest(request)){
                matchedGuids.add(reports.get(reportId).getGuid());
            }

            long endTime = System.currentTimeMillis();
            results.add(new ReportSearchResult(request, matchedGuids, endTime - startTime));
        }
        return results;
    }


    public ArrayList<Integer> searchByRequest(String request) {
        ArrayList<ArrayList<Integer>> setsOfReportsWhereWordsMatch = searchForSetOfReportsByWordsInDictionary(request);
        TruthTable relationOfFoundWords = new TruthTable(setsOfReportsWhereWordsMatch.size());
        ArrayList<Integer> getDocumentId = null;
        for (int i = 0; i < setsOfReportsWhereWordsMatch.size(); i++) {
            getDocumentId = ActionsOnMathematicalSets.сombineSetsWithSamePriority(setsOfReportsWhereWordsMatch, relationOfFoundWords.getCombinations(i));
            if (!getDocumentId.isEmpty()) {
                return getDocumentId;
            }
        }
        return getDocumentId;
    }
    public ArrayList<ArrayList<Integer>> searchForSetOfReportsByWordsInDictionary(String request){
        List<String> words = TextProcessing.split(request);
        ArrayList<ArrayList<Integer>> setsOfReportsWhereWordsMatch = new ArrayList<>();
        for (String word: words){
            ArrayList <Integer> searchResultSingleRootWords = searchByRootWordsInDictionary(word);
            if(!searchResultSingleRootWords.isEmpty()){
                setsOfReportsWhereWordsMatch.add(searchResultSingleRootWords);
            }
        }
        return setsOfReportsWhereWordsMatch;
    }
    public ArrayList<Integer> searchByRootWordsInDictionary(String word){
        ArrayList <String> singleRootWords = TextProcessing.findSingleRootWords(word);
        ArrayList <Integer> resultSingleRootWords = new ArrayList<>();
        for( String token : singleRootWords){
            if(dictionary.containsKey(token)){
                resultSingleRootWords.addAll(dictionary.get(token));
            }
        }
        return resultSingleRootWords;
    }

}