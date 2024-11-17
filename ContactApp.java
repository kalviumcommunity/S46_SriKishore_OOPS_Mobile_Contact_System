import java.util.Scanner;

// Class representing a Contact
class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private String altName;

    // Constructor to initialize Contact details
    public Contact(String name, String phoneNumber, String email, String altName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.altName = altName;
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

        // Cleaning up memory: simulating 'delete' by dereferencing
        for (int i = 0; i < contacts.length; i++) {
            contacts[i] = null; // Dereferencing each contact object
        }
        contacts = null; // Dereferencing the array
        System.out.println("\nAll contacts deleted. Memory will be reclaimed by the Garbage Collector.");

        scanner.close();
    }
}
