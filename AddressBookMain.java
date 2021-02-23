import java.util.*;

public class AddressBookMain implements MultipleAddressBook {
    public Map<String, ArrayList<AddressBook>> book;
    public Map<String, ArrayList<AddressBook>> multibook;
    public Map<String, ArrayList<AddressBook>> city;
    public Map<String, ArrayList<AddressBook>> state;
    public ArrayList<AddressBook> entries;
    Scanner obj = new Scanner(System.in);

    // Constructor
    public AddressBookMain() {
        book = new HashMap<>();
        multibook = new HashMap<>();
        city = new HashMap<>();
        state = new HashMap<>();
        entries = new ArrayList<>();
    }

    @Override
    public void addAddressBook(String BookName, String FirstName, String LastName, String Address, String City, int Zip,
                               String State, long PhoneNumber, String Email) {
        AddressBook adder = new AddressBook(BookName, FirstName, LastName, Address, City, Zip, State, PhoneNumber, Email);
        entries.add(adder);
        book.put(FirstName, entries);
        multibook.put(BookName, entries);
        city.put(City, entries);
        state.put(State, entries);
    }

    //This method takes console arguments
    @Override
    public void getContact() {
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
        if (equals(FirstName))
            addAddressBook(BookName, FirstName, LastName, Address, City, Zip, State, PhoneNumber, Email);
        else
            System.out.println("the Name already exist in contact please use different name");
    }

    @Override
    public boolean equals(String firstName) {
        List<AddressBook> details = book.get(firstName);
        if (details == null) return true;
        return false;
    }

    //    This method helps to edit the details
    @Override
    public void editContact() {
        System.out.println("enter your book name");
        String bookname = obj.next();
        ArrayList<AddressBook> option = multibook.get(bookname);
        System.out.println("enter your name");
        String name = obj.next();
        for (AddressBook details : option) {
            if (details.FirstName.equals(name)) {
                boolean conditon = true;
                while (conditon) {
                    System.out.println("enter number  1:first_name 2:last_name 3:address 4:City 5:zip 6:state 7:phone_number" +
                            " 8:email  0:quit");
                    int check = obj.nextInt();
                    switch (check) {
                        case 1:
                            System.out.println("Enter you first name");
                            String firstname = obj.next();
                            details.FirstName = firstname;
                            System.out.println(book);
                            break;
                        case 2:
                            System.out.println("Enter you last name");
                            String lastname = obj.next();
                            details.FirstName = lastname;
                            System.out.println(book);
                            break;
                        case 3:
                            System.out.println("Enter you address ");
                            String addressname = obj.next();
                            details.Address = addressname;
                            System.out.println(book);
                            break;
                        case 4:
                            System.out.println("Enter you City name");
                            String cityname = obj.next();
                            details.City = cityname;
                            System.out.println(book);
                            break;
                        case 5:
                            System.out.println("Enter you Zip name");
                            int zipname = obj.nextInt();
                            details.Zip = zipname;
                            System.out.println(book);
                            break;
                        case 6:
                            System.out.println("Enter you State name");
                            String statename = obj.next();
                            details.State = statename;
                            System.out.println(book);
                            break;
                        case 7:
                            System.out.println("Enter you Phone number");
                            long phonenumber = obj.nextLong();
                            obj.nextLine();
                            details.PhoneNumber = phonenumber;
                            System.out.println(book);
                            break;
                        case 8:
                            System.out.println("Enter you email");
                            String emailname = obj.next();
                            details.Email = emailname;
                            System.out.println(book);
                            break;
                        case 0:
                            conditon = false;
                            break;
                        default:
                            System.out.println("invalid input");
                    }
                }
            }
        }
    }

    // This method helps to delete the contact details
    @Override
    public void deleteEntry() {
        System.out.println("enter your name to delete from contact");
        String name = obj.next();
        book.remove(name);
    }

    // This method helps user to choose action
    public boolean makechoice() {
        System.out.println("enter 1:add_contact 2:view_by_city 3-view_by_state 4:edit_contact 5:delete_contact" +
                " 6:person_by_city_or_state or 0 to quit");
        int check = obj.nextInt();
        boolean conditon = true;
        switch (check) {
            case 1:
                getContact();
                break;
            case 2:
                viewPersonByCity();
                break;
            case 3:
                viewPersonByState();
                break;
            case 4:
                editContact();
                break;
            case 5:
                deleteEntry();
                break;
            case 6:
                getContactByCityOrState();
                break;
            case 0:
                conditon = false;
                break;
            default:
                System.out.println("invalid input");
        }
        return conditon;
    }

    public void viewPersonByCity() {
        System.out.println("Enter city");
        String location = obj.next();
        obj.nextLine();
        int flag = 1;
        for (String entry : city.keySet()) {
            if (entry.equals(location)) System.out.println(entry);
            flag = 0;
        }
        if (flag == 1) System.out.println("no records found");
    }
    public void viewPersonByState() {
        System.out.println("Enter state");
        String location = obj.next();
        obj.nextLine();
        int flag = 1;
        for (String entry : state.keySet()) {
            if (entry.equals(location)) System.out.println(entry);
            flag = 0;
        }
        if (flag == 1) System.out.println("no records found");
    }

    public void getContactByCityOrState() {
        System.out.println("Enter city or state");
        String location = obj.next();
        obj.nextLine();
        int flag = 1;
        for (String entry : multibook.keySet()) {
            for (AddressBook item : multibook.get(entry)) {
                if (item.State.equals(location) || item.City.equals(location)) {
                    System.out.println(item);
                    flag = 0;
                }
            }
        }
        if (flag == 1) System.out.println("no records found");
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