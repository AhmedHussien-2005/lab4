import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Abstract base class using Template Method Pattern
public abstract class Data {
    protected ArrayList<Record> records;
    protected String filename;

    public Data(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
        readFromFile();
    }

    // Template method - defines the algorithm structure
    public void readFromFile() {
        records.clear();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    Record record = createRecordFrom(line);
                    records.add(record);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            records = new ArrayList<>();
        }
    }

    // Abstract factory method - subclasses provide specific implementation
    protected abstract Record createRecordFrom(String line);

    public ArrayList<Record> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        return getRecord(key) != null;
    }

    public Record getRecord(String key) {
        for (Record record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(Record record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        records.removeIf(record -> record.getSearchKey().equals(key));
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Record record : records) {
                writer.println(record.lineRepresentation());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

