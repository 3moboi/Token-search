package org.example.io.reading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RequestReader {

    private final String filePath;

    public RequestReader(String file){
        this.filePath = file;
    }

    public ArrayList<String> readFile() {
        ArrayList <String> requests = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = br.readLine()) != null) {
                requests.add(line);
            }
        }
        catch(IOException exception){
            System.out.println("Ошибка чтения файла с запросами " + exception);
        }

        return requests;
    }
}
