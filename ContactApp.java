import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private String altName; 

    public Contact(String name, String phoneNumber, String email, String altName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.altName = altName; 
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAltName() {
        return this.altName;
    }

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
            contacts[i] = new Contact(name, phoneNumber, email, altName);
        }

        System.out.println("\nDisplaying all contacts:");
        for (Contact contact : contacts) {
            contact.displayContact();
        }

        scanner.close();
    }
}
