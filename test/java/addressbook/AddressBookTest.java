package addressbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.net.CookieHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AddressBookTest {

    @Test
    public void givenUsernamePassword_WhenConnected_ShouldReturnTrue() throws SQLException {
        AddressBookDB addressBookDB = new AddressBookDB();
        List<AddressBook> addressBookList = addressBookDB.readData();
        Assertions.assertEquals(2, addressBookList.size());
    }

    @Test
    public void givenNewState_whenUpdated_shouldSyncWithDB() throws SQLException {
        AddressBookDB addressBookDB = new AddressBookDB();
        int result = addressBookDB.updateEntry("viresh", "Goa");
        Assertions.assertEquals(1, result);
    }

    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchCount() {
        AddressBookDB addressBookDB = new AddressBookDB();
        LocalDate startDate = LocalDate.of(2018, 01, 01);
        LocalDate endDate = LocalDate.now();
        List<AddressBook> contactList = addressBookDB.contactForRange(startDate, endDate);
        Assert.assertEquals(2, contactList.size());
    }

    @Test
    public void givenCityName_ShouldReturnData_AndMatchCount() {
        AddressBookDB addressBookDB = new AddressBookDB();
        List<AddressBook> contactList = addressBookDB.getContactsByCity("mumbai");
        Assert.assertEquals(1, contactList.size());
    }

    @Test
    public void givenStateName_ShouldReturnData_AndMatchCount() {
        AddressBookDB addressBookDB = new AddressBookDB();
        List<AddressBook> contactList = addressBookDB.getContactsByState("maharashtra");
        Assert.assertEquals(1, contactList.size());
    }

    @Test
    public void givenInfo_ShouldBeAbleToAddDataInDB() {
        AddressBookDB addressBookDB = new AddressBookDB();
        List<AddressBook> contactList = addressBookDB.addContact("abc", "sidhu", "rawool", "parel",
                "pune", "400012", "goa", "1234567", "asdfg@234");
        Assert.assertEquals(3, contactList.size());
    }

    @Test
    public void given6Employees_whenAddedShouldBeAddedToTheDB() throws SQLException {
        AddressBookDB addressBookDB = new AddressBookDB();
        AddressBook[] arrayOfEmps = {
                new AddressBook("abc", "pooja", "rawool", "parel",
                        "pune", "400012", "goa", "1234567", "asdfg@234"),
                new AddressBook("abc", "sidhu", "rawool", "parel",
                        "pune", "400012", "goa", "1234567", "asdfg@234")
        };
        Instant start = Instant.now();
        addressBookDB.addContactWithThreads(Arrays.asList(arrayOfEmps));
        Instant end = Instant.now();
        System.out.println("Duration without Thread : " + Duration.between(start, end));
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
    }

    @Test
    public void givenEntryInJson_Whenretrived_ShouldMatchCount() {
        List<AddressBook> contactsList = getContactList();
        Assert.assertEquals(2,contactsList.size());
    }

    private List<AddressBook> getContactList() {
        Response response = RestAssured.get("/addressbook");
        List<AddressBook> contactsList = new Gson().fromJson(response.asString(),new TypeToken<List<AddressBook>>(){}.getType());
        return contactsList;
    }
}




