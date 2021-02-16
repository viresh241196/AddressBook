interface MultipleAddressBook {
    public void addAddressBook(String BookName,String FirstName, String LastName, String Address, String City, int Zip,String State, long PhoneNumber, String Email);
    public void getAddressBookByName();
    public void getContactByName();
    public void getContact();
    public void editContact();
    public void deleteEntry();
    public boolean makechoice();
}

 class AddressBook{
    public  String BookName;
    public  String FirstName;
    public  String LastName;
    public  String Address;
    public  String City;
    public  int Zip;
    public  String State;
    public  long PhoneNumber;
    public  String Email;

    public AddressBook(String BookName,String FirstName, String LastName, String Address, String City, int Zip,String State, long PhoneNumber, String Email){
        this.BookName=BookName;
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.Address=Address;
        this.City=City;
        this.Zip=Zip;
        this.State=State;
        this.PhoneNumber=PhoneNumber;
        this.Email=Email;
    }
    @Override
    public String toString(){
        return BookName+" "+FirstName+" "+LastName+" "+Address+" "+City+" "+Zip+" "+State+" "+PhoneNumber+" "+Email;
    }
}
