import java.util.*;

public class AddressBookMain implements MultipleAddressBook {
    public Map<String, AddressBook> contact;
    public Map<String, AddressBook> book;

    // Constructor
    public AddressBookMain() {
        contact = new HashMap<>();
        book = new HashMap<>();
    }

    @Override
    public void addAddressBook(String BookName, String FirstName, String LastName, String Address, String City, int Zip, String State, long PhoneNumber, String Email) {
        AddressBook adder = new AddressBook(BookName, FirstName, LastName, Address, City, Zip, State, PhoneNumber, Email);
        contact.put(FirstName, adder);
        book.put(BookName, adder);
    }

    //This method takes console arguments
    @Override
    public void getContact() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter Address Book Name");
        String BookName = obj.next();
        System.out.println("Enter you first name");
        String FirstName = obj.next();
        System.out.println("Enter you last name");
        String LastName = obj.next();
        obj.nextLine();
        System.out.println("Enter you Address name");
        String Address = obj.nextLine();
        System.out.println("Enter you zip ");
        int Zip = obj.nextInt();
        System.out.println("Enter you city name");
        String City = obj.next();
        System.out.println("Enter you state name");
        String State = obj.next();
        obj.nextLine();
        System.out.println("Enter you phone number");
        long PhoneNumber = obj.nextLong();
        obj.nextLine();
        System.out.println("Enter you email name");
        String Email = obj.nextLine();
        addAddressBook(BookName, FirstName, LastName, Address, City, Zip, State, PhoneNumber, Email);
    }

    // This method helps to edit the details
    @Override
    public void editContact() {
        Scanner obj = new Scanner(System.in);
        System.out.println("enter your name");
        String name = obj.next();
        AddressBook option = contact.get(name);
        boolean conditon = true;
        while (conditon) {
            System.out.println("enter number to edit 0-firstname 1-lastname 2-address 3-zip 4-city 5-state 6-phonenumber 7-email" +
                    " 9 to quit");
            int check = obj.nextInt();
            switch (check) {
                case 0:
                    System.out.println("Enter you first name");
                    option.FirstName = obj.next();
                    break;
                case 1:
                    System.out.println("Enter you last name");
                    option.LastName = obj.next();
                    obj.nextLine();
                    break;
                case 2:
                    System.out.println("Enter you Address name");
                    option.Address = obj.nextLine();
                    break;
                case 3:
                    System.out.println("Enter you zip ");
                    option.Zip = obj.nextInt();
                    break;
                case 4:
                    System.out.println("Enter you city name");
                    option.City = obj.next();
                    break;
                case 5:
                    System.out.println("Enter you state name");
                    option.State = obj.next();
                    obj.nextLine();
                    break;
                case 6:
                    System.out.println("Enter you phone number");
                    option.PhoneNumber = obj.nextLong();
                    obj.nextLine();
                    break;
                case 7:
                    System.out.println("Enter you email name");
                    option.Email = obj.next();
                    break;
                case 9:
                    conditon = false;
                    break;
                default:
                    System.out.println("invalid input");
            }
        }
    }

    // This method helps to delete the contact details
    @Override
    public void deleteEntry() {
        Scanner obj = new Scanner(System.in);
        System.out.println("enter your name to delete from contact");
        String name = obj.next();
        contact.remove(name);
    }

    // This method helps user to choose action
    public boolean makechoice() {
        Scanner obj = new Scanner(System.in);
        System.out.println("enter 1 - add contact 2 -display contact 3-display address book 4 - edit 5 - delete entry or 0 to quit");
        int check = obj.nextInt();
        boolean conditon = true;
        switch (check) {
            case 1:
                getContact();
                break;
            case 2:
                getContactByName();
                break;
            case 3:
                getAddressBookByName();
                break;
            case 4:
                editContact();
                break;
            case 5:
                deleteEntry();
                break;
            case 0:
                conditon = false;
                break;
            default:
                System.out.println("invalid input");
        }
        return conditon;
    }

    // This method helps to find contact details by first name
    public void getContactByName() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter Name to search");
        String firstName = obj.nextLine();
        AddressBook details = contact.get(firstName);
        System.out.println(details);
    }

    // This method helps to find address book details by book name
    public void getAddressBookByName() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter Address Book Name to search");
        String bookName = obj.nextLine();
        AddressBook details = book.get(bookName);
        System.out.println(details);
    }

    public static void main(String[] args) {
        MultipleAddressBook bookBuilder = new AddressBookMain();
        bookBuilder.makechoice();
        boolean conditon = true;
        while (conditon) {
            boolean condition = bookBuilder.makechoice();
            if (condition == false)
                break;
        }
    }
}
