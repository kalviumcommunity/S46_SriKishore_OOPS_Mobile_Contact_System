import java.util.Scanner;

// Class representing a Contact
class Contact {
    private String name;        // Private: Encapsulation and abstraction
    private String phoneNumber; // Private: Implementation hidden
    private String email;       // Private: Implementation hidden
    private String altName;     // Private: Implementation hidden

    // Constructor to initialize Contact details (public interface)
    public Contact(String name, String phoneNumber, String email, String altName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.altName = altName;
    }

    // Public accessor and mutator for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Public accessor and mutator for Phone Number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Public accessor and mutator for Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Public method to display contact details (abstraction: user doesn't know internal representation)
    public void displayContact() {
        System.out.println("=====================================");
        System.out.println("Contact Details:");
        System.out.printf("Name: %-25s\n", this.name);
        System.out.printf("Phone Number: %-25s\n", this.phoneNumber);
        System.out.printf("Email: %-25s\n", this.email);
        if (this.altName != null && !this.altName.isEmpty()) {
            System.out.printf("Alternative Name: %-25s\n", this.altName);
        }
        System.out.println("=====================================");
    }
}

// Class to manage contacts
class ContactManager {
    private Contact[] contacts; // Private: Implementation hidden from the user
    private int contactCount = 0;

    // Constructor to initialize ContactManager with the maximum number of contacts
    public ContactManager(int maxContacts) {
        this.contacts = new Contact[maxContacts];
    }

    // Public method to add a new contact
    public void addContact(Contact contact) {
        if (contactCount < contacts.length) {
            contacts[contactCount++] = contact;
        } else {
            System.out.println("Cannot add more contacts, storage full!");
        }
    }

    // Public method to display all contacts
    public void displayAllContacts() {
        System.out.println("\nDisplaying all contacts:");
        for (int i = 0; i < contactCount; i++) {
            contacts[i].displayContact();
        }
    }

    // Public method to search for a contact by name
    public void searchContactByName(String name) {
        boolean found = false;
        System.out.println("\nSearching for contact with name: " + name);
        for (int i = 0; i < contactCount; i++) {
            if (contacts[i].getName().equalsIgnoreCase(name)) {
                contacts[i].displayContact();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No contact found with the name: " + name);
        }
    }
}

public class ContactApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many contacts do you want to store? ");
        int maxContacts = Integer.parseInt(scanner.nextLine());

        ContactManager manager = new ContactManager(maxContacts); // Using ContactManager to abstract details

        // Adding contacts
        for (int i = 0; i < maxContacts; i++) {
            System.out.println("\nEnter details for contact " + (i + 1) + ":");

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Alternative Name (optional): ");
            String altName = scanner.nextLine();

            // Creating contact and adding to the manager
            Contact contact = new Contact(name, phoneNumber, email, altName);
            manager.addContact(contact);
        }

        // Displaying all contacts
        manager.displayAllContacts();

        // Searching for a contact
        System.out.print("\nEnter a name to search for: ");
        String searchName = scanner.nextLine();
        manager.searchContactByName(searchName);

        scanner.close();
    }
}
