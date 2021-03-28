package addressbook;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDB {

    public synchronized Connection getConnection() throws SQLException {
        String jdbcURl = "jdbc:mysql://localhost:3306/address_book?useSSl=false";
        String userName = "root";
        String password = "root";
        Connection connection;
        System.out.println("Connecting to database:" + jdbcURl);
        connection = DriverManager.getConnection(jdbcURl, userName, password);
        System.out.println("connection is successful!!!!!!!" + connection);
        return connection;
    }

    public List<AddressBook> readData() {
        String sql = "select * from address_book;";
        List<AddressBook> contactList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactList = this.fetchData(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactList;
    }

    public int updateEntry(String name, String state) {
        String sql = String.format("update address_book set state = '%s' where name = '%s';",
                state, name);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<AddressBook> contactForRange(LocalDate start, LocalDate end) {
        List<AddressBook> contactList = new ArrayList<>();
        String sql = String.format("select * from address_book where dateAdded between '%s' and '%s';", start, end);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactList = this.fetchData(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactList;
    }

    private List<AddressBook> fetchData(ResultSet resultSet) {
        List<AddressBook> contactsList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String bookName = resultSet.getString("bookName");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zip = resultSet.getString("zip");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                contactsList.add(new AddressBook(bookName, firstName, lastName, address, city, zip, state, phoneNumber,
                        email));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactsList;
    }

    public List<AddressBook> getContactsByCity(String city) {
        List<AddressBook> contactList = new ArrayList<>();
        String sql = String.format("select * from address_book where city = '%s';", city);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactList = this.fetchData(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactList;
    }

    public List<AddressBook> getContactsByState(String state) {
        List<AddressBook> contactList = new ArrayList<>();
        String sql = String.format("select * from address_book where state = '%s';", state);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactList = this.fetchData(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactList;
    }
}
