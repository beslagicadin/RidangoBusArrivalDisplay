package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReader {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    private FileReader() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static List<String[]> readFile(String filepath) {
        List<String[]> data = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filepath));
            for (int i = 1; i < lines.size(); i++) {
                String[] details = lines.get(i).split(",");
                data.add(details);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading file: {0}", filepath);
        }
        return data;
    }
}
