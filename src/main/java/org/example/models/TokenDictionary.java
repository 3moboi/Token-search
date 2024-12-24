package org.example.models;

import org.example.models.Report;
import org.example.utility.TextProcessing;

import java.util.*;

public class TokenDictionary {

    //чек лист
    //что-то решить с однокоренными словами
    private final Map <String, ArrayList<Integer>> dictionary = new HashMap<>();

    public TokenDictionary(ArrayList <Report> reports){
        int i = 0;
        for(Report report : reports){
            add(report.getDescription(), i++);
        }
    }

    public void add(String description, int idOfLineInDocument){
        //обработка текста
        List<String> words = TextProcessing.split(description);

        for (String word: words){

            ArrayList <Integer> stringsContainingKey;

            if(dictionary.containsKey(word)){
                stringsContainingKey = dictionary.get(word);
            }
            else{
                stringsContainingKey = new ArrayList<Integer>();
            }

            stringsContainingKey.add(idOfLineInDocument);
            dictionary.put(word, stringsContainingKey);
        }
    }

    public Map <String,ArrayList<Integer>> getDictionary(){
        return dictionary;
    }

}
