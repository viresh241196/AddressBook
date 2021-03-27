package addressbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Json {
    private static String JSON_PATH = "src/main/resources/contact.json";

    public void writeJson(ArrayList<AddressBook> contacts) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(contacts);
            FileWriter writer = new FileWriter(JSON_PATH);
            writer.write(json);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readJson() {
        Type REVIEW_TYPE = new TypeToken<List<AddressBook>>() {
        }.getType();
        List<AddressBook> contactList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(JSON_PATH));
            contactList = gson.fromJson(reader, REVIEW_TYPE);
            contactList.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
