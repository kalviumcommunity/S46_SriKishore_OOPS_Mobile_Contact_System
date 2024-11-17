import java.util.Scanner;

// Class representing a Contact
class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private String altName;

    // Static variables
    private static int totalContacts = 0;       // Count of total contacts created
    private static int contactsWithAltName = 0; // Count of contacts with an alternative name

    // Constructor to initialize Contact details
    public Contact(String name, String phoneNumber, String email, String altName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.altName = altName;

        // Increment totalContacts whenever a new Contact is created
        totalContacts++;

        // Increment contactsWithAltName if an alternative name is provided
        if (altName != null && !altName.isEmpty()) {
            contactsWithAltName++;
        }
    }

    // Static method to get the total count of contacts
    public static int getTotalContacts() {
        return totalContacts;
    }

    // Static method to get the count of contacts with an alternative name
    public static int getContactsWithAltName() {
        return contactsWithAltName;
    }

    // Static member function to calculate the percentage of contacts with alternative names
    public static double calculateAltNamePercentage() {
        if (totalContacts == 0) {
            return 0.0;
        }
        return (contactsWithAltName * 100.0) / totalContacts;
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

        // Dynamically allocating memory for Contact objects using 'new'
        Contact[] contacts = new Contact[numContacts];

        for (int i = 0; i < numContacts; i++) {
            System.out.println("\nEnter details for contact " + (i + 1) + ":");

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine();

            // Mandatory email
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            // Alternative name (optional)
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

        // Displaying summary using static variables and functions
        System.out.println("\nSummary:");
        System.out.println("=====================================");
        System.out.println("Total Contacts Created: " + Contact.getTotalContacts());
        System.out.println("Contacts with Alternative Names: " + Contact.getContactsWithAltName());
        System.out.printf("Percentage of Contacts with Alternative Names: %.2f%%\n", Contact.calculateAltNamePercentage());
        System.out.println("=====================================");

        // Cleaning up memory: simulating 'delete' by dereferencing
        for (int i = 0; i < contacts.length; i++) {
            contacts[i] = null; // Dereferencing each contact object
        }
        contacts = null; // Dereferencing the array
        System.out.println("\nAll contacts deleted. Memory will be reclaimed by the Garbage Collector.");

        scanner.close();
    }
}
