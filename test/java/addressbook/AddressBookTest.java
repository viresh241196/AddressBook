package addressbook;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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
}
//        EmployeePayrollData[] arrayOfEmps = {
//                new EmployeePayrollData(0, "chandler bing", "M", 70000, LocalDate.now())
//        };
//        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
//        employeePayrollService.readEmployeePayRollDBData(DB_IO);
//        Instant start = Instant.now();
//        employeePayrollService.addEmployeesToPayroll(Arrays.asList(arrayOfEmps));
//        Instant end = Instant.now();
//        System.out.println("Duration without Thread : " + Duration.between(start, end));
//        Instant threadStart = Instant.now();
//        employeePayrollService.addEmployeeToPayrollWithThread(Arrays.asList(arrayOfEmps));
//        Instant threadEnd = Instant.now();
//        System.out.println("Duration without Thread : " + Duration.between(threadStart, threadEnd));
//        Assertions.assertEquals(24, employeePayrollService.countEntries(DB_IO));


