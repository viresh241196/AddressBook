import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class AddressBookMain {
    private String FirstName;
    private String LastName;
    private String Address;
    private int Zip;
    private String City;
    private String State;
    private long PhoneNumber;
    private String Email;
    private HashMap contact;

    public AddressBookMain(String FirstName, String LastName, String Address, String City, int Zip,
                           String State, long PhoneNumber, String Email) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Address = Address;
        this.Zip = Zip;
        this.City = City;
        this.State = State;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
    }

    public AddressBookMain() {
    }

    private static void getContact(AddressBookMain details) {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter you first name");
        details.FirstName = obj.next();
        System.out.println("Enter you last name");
        details.LastName = obj.next();
        obj.nextLine();
        System.out.println("Enter you Address name");
        details.Address = obj.nextLine();
        System.out.println("Enter you zip ");
        details.Zip = obj.nextInt();
        System.out.println("Enter you city name");
        details.City = obj.next();
        System.out.println("Enter you state name");
        details.State = obj.next();
        obj.nextLine();
        System.out.println("Enter you phone number");
        details.PhoneNumber = obj.nextLong();
        obj.nextLine();
        System.out.println("Enter you email name");
        details.Email = obj.nextLine();
    }

    public static void editContact(HashMap contact) {
        Scanner obj = new Scanner(System.in);
        System.out.println("enter your name");
        String name = obj.next();
        ArrayList option = (ArrayList) (contact.get(name));
        boolean conditon = true;
        while (conditon) {
            System.out.println("enter number to edit 0-firstname 1-lastname 2-address 3-zip 4-city 5-state 6-phonenumber 7-email" +
                    " 9 to quit");
            int check = obj.nextInt();
            switch (check) {
                case 0:
                    System.out.println("Enter you first name");
                    option.set(0, obj.next());

                    break;
                case 1:
                    System.out.println("Enter you last name");
                    option.set(1, obj.next());
                    obj.nextLine();
                    break;
                case 2:
                    System.out.println("Enter you Address name");
                    option.set(2, obj.nextLine());
                    break;
                case 3:
                    System.out.println("Enter you zip ");
                    option.set(3, obj.nextInt());
                    break;
                case 4:
                    System.out.println("Enter you city name");
                    option.set(4, obj.next());
                    break;
                case 5:
                    System.out.println("Enter you state name");
                    option.set(5, obj.next());
                    obj.nextLine();
                    break;
                case 6:
                    System.out.println("Enter you phone number");
                    option.set(6, obj.nextLong());
                    obj.nextLine();
                    break;
                case 7:
                    System.out.println("Enter you email name");
                    option.set(7, obj.next());
                    break;
                case 9:
                    conditon = false;
                    break;
                default:
                    System.out.println("invalid input");
            }
            if (name != (contact.get(name))) {
                Object newname = option.get(0);
                contact.remove(name);
                contact.put(newname, option);
            }
        }
    }

    public HashMap storeContact(HashMap contact) {
        ArrayList<String> entry = new ArrayList<String>();
        entry.add(FirstName);
        entry.add(LastName);
        entry.add(Address);
        entry.add(String.valueOf(Zip));
        entry.add(City);
        entry.add(State);
        entry.add(String.valueOf(PhoneNumber));
        entry.add(Email);
        contact.put(FirstName, entry);
        System.out.println(entry);
        System.out.println(contact);
        return contact;
    }

    @Override
    public String toString() {
        return FirstName;
    }

    public static boolean makechoice(AddressBookMain contact1, HashMap contact) {
        Scanner obj = new Scanner(System.in);
        System.out.println("enter 1 - add contact 2 -display contact 3 - edit 4 - delete entry or 0 to quit");
        int check = obj.nextInt();
        boolean conditon = true;
        switch (check) {
            case 1:
                getContact(contact1);
                HashMap<String, ArrayList<String>> data = contact1.storeContact(contact);
                contact = data;
                break;
            case 2:
                displayStore(contact);
                break;
            case 3:
                editContact(contact);
                break;
            case 4:
                deleteEntry(contact);
                break;
            case 0:
                conditon = false;
                break;
            default:
                System.out.println("invalid input");
        }
        return conditon;
    }

    public static void displayStore(HashMap contact) {
        System.out.println(contact);
    }

    public static void deleteEntry(HashMap contact){
        Scanner obj = new Scanner(System.in);
        System.out.println("enter your name to delete from contact");
        String name = obj.next();
        contact.remove(name);
    }
    public static void main(String[] args) {
        AddressBookMain contact1 = new AddressBookMain();
        HashMap<String, ArrayList<String>> contact = new HashMap<String, ArrayList<String>>();
        boolean conditon = true;
        while (conditon) {
            boolean condition = makechoice(contact1, contact);
            if (condition == false)
                break;
        }
    }
}
