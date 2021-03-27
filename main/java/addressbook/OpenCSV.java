package addressbook;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OpenCSV {
    public static final String SAMPLE_CSV_FILE_PATH = "src/main/resources/write_csv.csv";

    public void readCSV() throws IOException {
        try {
            CSVReader reader = new CSVReader(new FileReader(SAMPLE_CSV_FILE_PATH));
            StringBuffer buffer = new StringBuffer();
            String line[];
            while ((line = reader.readNext()) != null) {
                for (int index = 0; index < line.length; index++)
                    System.out.println(line[index] + "  ");
                System.out.println("  ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String OBJECT_LIST = "src/main/resources/write_csv.csv";

    public void writeCSV(ArrayList<AddressBook> contactList) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try (Writer writer = Files.newBufferedWriter(Paths.get(OBJECT_LIST));
        ) {
            StatefulBeanToCsv<AddressBook> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            List<AddressBook> csvUsers = new ArrayList<>();
            for (AddressBook contact : contactList) {
                csvUsers.add(new AddressBook(contact.getBookName(), contact.getFirstName(), contact.getLastName(), contact.getAddress(),
                        contact.getCity(), contact.getZip(), contact.getState(), contact.getPhoneNumber(), contact.getEmail()));
            }
            beanToCsv.write(csvUsers);
        }
    }
}