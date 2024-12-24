package org.example.io.writing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.ReportSearchResult;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFileWriting {
    public void writeOutput(String outputPath, long initTime, List<ReportSearchResult> searchResults) throws IOException {
        Map<String, Object> output = new HashMap<>();
        output.put("initTime", initTime);
        output.put("result", searchResults);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(outputPath), output);
    }
}
