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

