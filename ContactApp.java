import java.util.Scanner;

// Class representing a Contact
class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private String altName;

    private static int totalContacts = 0; // Static variable to count total contacts

    // Constructor to initialize Contact details
    public Contact(String name, String phoneNumber, String email, String altName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.altName = altName;

        // Increment totalContacts whenever a new Contact is created
        totalContacts++;
    }

    // Static method to get total contacts count
    public static int getTotalContacts() {
        return totalContacts;
    }

    // Getter and Setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Phone Number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter for Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for Alternative Name
    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    // Static method to search for a contact by name
    public static void searchContactByName(Contact[] contacts, String searchName) {
        boolean found = false;
        System.out.println("\nSearching for contact with name: " + searchName);
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(searchName)) {
                contact.displayContact();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No contact found with the name: " + searchName);
        }
    }

    // Method to display contact details
    public void displayContact() {
        System.out.println("=====================================");
        System.out.println("Contact Details:");
        System.out.println("=====================================");
        System.out.printf("Name: %-25s\n", this.name);
        System.out.printf("Phone Number: %-25s\n", this.phoneNumber);
        System.out.printf("Email: %-25s\n", this.email);
        if (this.altName != null && !this.altName.isEmpty()) {
            System.out.printf("Alternative Name: %-25s\n", this.altName);
        }
        System.out.println("=====================================");
    }
}

public class ContactApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many contacts do you want to add? ");
        int numContacts = Integer.parseInt(scanner.nextLine());

        // Dynamically allocating memory for Contact objects
        Contact[] contacts = new Contact[numContacts];

        for (int i = 0; i < numContacts; i++) {
            System.out.println("\nEnter details for contact " + (i + 1) + ":");

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Alternative Name (optional): ");
            String altName = scanner.nextLine();

            // Creating each Contact dynamically
            contacts[i] = new Contact(name, phoneNumber, email, altName);
        }

        // Displaying all contacts
        System.out.println("\nDisplaying all contacts:");
        for (Contact contact : contacts) {
            contact.displayContact();
        }

        // Demonstrating encapsulation: Updating a contact
        System.out.print("\nEnter the index of the contact to update (1 to " + numContacts + "): ");
        int updateIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (updateIndex >= 0 && updateIndex < numContacts) {
            Contact contactToUpdate = contacts[updateIndex];

            System.out.println("Updating contact: " + contactToUpdate.getName());

            System.out.print("Enter new Phone Number: ");
            String newPhoneNumber = scanner.nextLine();
            contactToUpdate.setPhoneNumber(newPhoneNumber);

            System.out.print("Enter new Email: ");
            String newEmail = scanner.nextLine();
            contactToUpdate.setEmail(newEmail);

            System.out.println("\nUpdated Contact Details:");
            contactToUpdate.displayContact();
        } else {
            System.out.println("Invalid index.");
        }

        // Static function usage: Search for a contact by name
        System.out.print("\nEnter a name to search for: ");
        String searchName = scanner.nextLine();
        Contact.searchContactByName(contacts, searchName);

        scanner.close();
    }
}
