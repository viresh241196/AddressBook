package addressbook;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookDB {

    public synchronized Connection getConnection() throws SQLException {
        String jdbcURl = "jdbc:mysql://localhost:3306/address_book?useSSl=false";
        String userName = "root";
        String password = "root";
        System.out.println("Connecting to database:" + jdbcURl);
        try {
            Connection connection = DriverManager.getConnection(jdbcURl, userName, password);
            System.out.println("connection is successful!!!!!!!" + connection);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public List<AddressBook> addContact(String bookName, String firstName, String lastName, String address, String city, String zip,
                                        String state, String phoneNumber, String email) {
        String sql = String.format("INSERT INTO address_book (bookName,firstName,lastName,address,city,state,zip,phone_number,email,dateAdded)" +
                        "values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')", bookName, firstName,
                lastName, address, city, zip, state, phoneNumber, email, LocalDate.MIN);
        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return readData();
    }

    public void addContactWithThreads(List<AddressBook> contactListData) {
        Map<Integer, Boolean> contactStatus = new HashMap<>();
        for (AddressBook entry : contactListData) {
            Runnable runnable = () -> {
                contactStatus.put(entry.hashCode(), false);
                System.out.println("Employee Being Added : " + Thread.currentThread().getName());
                List<AddressBook> list = new AddressBookDB().addContact(entry.getBookName(), entry.getFirstName(), entry.getLastName(),
                        entry.getAddress(), entry.getCity(), entry.getState(), entry.getZip(), entry.getPhoneNumber(),
                        entry.getEmail());
                contactStatus.put(entry.hashCode(), true);
                System.out.println("Employee Added : " + Thread.currentThread().getName());
            };
            Thread thread = new Thread(runnable, entry.getFirstName());
            thread.start();
        }
        while (contactStatus.containsValue(false)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
