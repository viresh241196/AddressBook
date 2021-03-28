package addressbook;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.net.CookieHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AddressBookTest {

    @Test
    public void givenUsernamePassword_WhenConnected_ShouldReturnTrue() throws SQLException {
        AddressBookDB addressBookDB = new AddressBookDB();
        List<AddressBook> addressBookList = addressBookDB.readData();
        Assertions.assertEquals(2, addressBookList.size());
    }
}
