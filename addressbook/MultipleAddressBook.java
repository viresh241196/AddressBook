package addressbook;

interface MultipleAddressBook {

    public void addAddressBook(String bookName, String firstName, String lastName, String address, String city, int zip,
                               String state, long phoneNumber, String email);

    public void getContact();

    boolean equals(String firstName);

    public void editContact();

    public void deleteEntry();

    public boolean makeChoice();

}

class AddressBook {
    public String bookName;
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public int zip;
    public String state;
    public long phoneNumber;
    public String email;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressBook(String bookName, String firstName, String lastName, String address, String city, int zip,
                       String state, long phoneNumber, String email) {
        this.bookName = bookName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "BookName: "+bookName + " firstName:" + firstName + " lastName:" + lastName + " address:" + address + " city:" + city
                + " zip:" + zip + " state:" + state + " phoneNumber:" + phoneNumber + " email:" + email+"\n";
    }
}
