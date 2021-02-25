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

    public AddressBook(){
    }

    @Override
    public String toString() {
        return bookName + " " + firstName + " " + lastName + " " + address + " " + city + " " + zip + " " + state + " "
                + phoneNumber + " " + email;
    }
}
