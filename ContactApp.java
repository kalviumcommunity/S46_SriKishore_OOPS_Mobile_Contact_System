import java.util.Scanner;

// Class representing a Contact
class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private String altName;

    // Default constructor
    public Contact() {
        this.name = "Unknown";
        this.phoneNumber = "Not Provided";
        this.email = "Not Provided";
        this.altName = "";
        System.out.println("Default Constructor Called: Contact initialized with default values.");
    }

    // Parameterized constructor
    public Contact(String name, String phoneNumber, String email, String altName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.altName = altName;
        System.out.println("Parameterized Constructor Called: Contact initialized with provided values.");
    }

    // Public method to display contact details
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

public class ContactApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating a Contact using the default constructor
        System.out.println("\nCreating a Contact using the Default Constructor:");
        Contact defaultContact = new Contact();
        defaultContact.displayContact();

        // Creating a Contact using the parameterized constructor
        System.out.println("\nCreating a Contact using the Parameterized Constructor:");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Alternative Name (optional): ");
        String altName = scanner.nextLine();

        Contact paramContact = new Contact(name, phoneNumber, email, altName);
        paramContact.displayContact();

        // Demonstrating cleanup logic (simulating destructor behavior)
        System.out.println("\nSimulating Cleanup Logic:");
        paramContact = null; // Dereference the object
        System.gc(); // Request garbage collection
        System.out.println("Garbage Collection requested for paramContact.");

        scanner.close();
    }

    // Simulating cleanup logic for Contact (Optional, for demonstration only)
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize method called: Cleaning up resources.");
        super.finalize();
    }
}
