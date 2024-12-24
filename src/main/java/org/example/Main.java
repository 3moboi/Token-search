package org.example;



import org.example.io.reading.CSVFileReader;
import org.example.io.reading.RequestReader;
import org.example.io.writing.JSONFileWriting;
import org.example.models.Report;
import org.example.models.ReportSearchResult;
import org.example.search.ReportSearcher;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        String dataFilePath = null;
        String inputFilePath = null;
        String outputFilePath = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--data":
                    dataFilePath = args[++i];
                    break;
                case "--input-file":
                    inputFilePath = args[++i];
                    break;
                case "--output-file":
                    outputFilePath = args[++i];
                    break;
            }
        }


        try {
            // Инициализация времени
            long initStartTime = System.currentTimeMillis();

            //Читаем файл с данными для документов
            ArrayList<Report> dataReport = new CSVFileReader(dataFilePath).readFile();
            //Читаем файл с запросами
            ArrayList <String> requests = new RequestReader(inputFilePath).readFile();


            ReportSearcher reportSearcher = new ReportSearcher(dataReport);

            long initEndTime = System.currentTimeMillis();

            List<ReportSearchResult> searchResults = reportSearcher.search(requests);

            JSONFileWriting jsonFileWriting = new JSONFileWriting();
            jsonFileWriting.writeOutput(outputFilePath, initEndTime - initStartTime, searchResults);

        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
