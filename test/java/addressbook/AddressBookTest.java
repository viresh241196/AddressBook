package addressbook;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.net.CookieHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
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
        int result =addressBookDB.updateEntry("viresh","Goa");
        Assertions.assertEquals(1, result);
    }

    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchCount() {
        AddressBookDB addressBookDB = new AddressBookDB();
        LocalDate startDate = LocalDate.of(2018, 01, 01);
        LocalDate endDate = LocalDate.now();
        List<AddressBook> contactList = addressBookDB.contactForRange(startDate,endDate);
        Assert.assertEquals(2,contactList.size());
    }
}
