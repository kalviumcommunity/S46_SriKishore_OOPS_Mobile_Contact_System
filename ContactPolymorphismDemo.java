import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

// Base Class with Polymorphic Methods
abstract class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    // Constructor Overloading - Default Constructor
    public Contact() {
        this.name = "Unknown";
        this.phoneNumber = "Not Provided";
        this.email = "Not Provided";
        System.out.println("Default Constructor Called: Contact initialized with default values.");
    }

    // Constructor Overloading - Parameterized Constructor
    public Contact(String name) {
        this(name, "Not Provided", "Not Provided");
        System.out.println("Single Param Constructor: Name initialized");
    }

    // Constructor Overloading - Parameterized Constructor with two parameters
    public Contact(String name, String phoneNumber) {
        this(name, phoneNumber, "Not Provided");
        System.out.println("Two Param Constructor: Name and Phone initialized");
    }

    // Full Parameterized Constructor
    public Contact(String name, String phoneNumber, String email) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        System.out.println("Full Parameterized Constructor Called: Contact initialized with provided values.");
    }

    // Polymorphic Method - Method Overloading for Display
    public void displayContact() {
        displayContact(false);
    }

    // Polymorphic Method - Overloaded display with detailed option
    public void displayContact(boolean detailed) {
        System.out.println("=====================================");
        System.out.println("Contact Details:");
        System.out.printf("Name: %-25s\n", this.name);
        
        if (detailed) {
            System.out.printf("Phone Number: %-25s\n", this.phoneNumber);
            System.out.printf("Email: %-25s\n", this.email);
            System.out.println("Additional Details:");
        }
        System.out.println("=====================================");
    }

    // Abstract method to be implemented by child classes
    public abstract String getContactType();

    // Getters and Setters (previous validation logic remains the same)
    public void setName(String name) {
        this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = (isValidPhoneNumber(phoneNumber)) ? phoneNumber : "Not Provided";
    }

    public void setEmail(String email) {
        this.email = (isValidEmail(email)) ? email : "Not Provided";
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }

    private boolean isValidEmail(String email) {
        if (email == null) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    // Getters
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
}

// Personal Contact with Polymorphic Behavior
class PersonalContact extends Contact {
    private String relationship;

    // Constructor Overloading
    public PersonalContact() {
        super();
        this.relationship = "Not Specified";
    }

    public PersonalContact(String name) {
        super(name);
        this.relationship = "Not Specified";
    }

    public PersonalContact(String name, String phoneNumber) {
        super(name, phoneNumber);
        this.relationship = "Not Specified";
    }

    public PersonalContact(String name, String phoneNumber, String email, String relationship) {
        super(name, phoneNumber, email);
        setRelationship(relationship);
    }

    // Polymorphic Method Overriding
    @Override
    public void displayContact() {
        super.displayContact(true);
        System.out.printf("Relationship: %-25s\n", this.relationship);
        System.out.println("=====================================");
    }

    // Implementing Abstract Method
    @Override
    public String getContactType() {
        return "Personal Contact";
    }

    // Setter for relationship
    public void setRelationship(String relationship) {
        this.relationship = (relationship == null || relationship.trim().isEmpty()) 
            ? "Not Specified" 
            : relationship.trim();
    }

    public String getRelationship() {
        return relationship;
    }
}

// Business Contact with Polymorphic Behavior
class BusinessContact extends PersonalContact {
    private String company;
    private String designation;

    // Constructor Overloading
    public BusinessContact() {
        super();
        this.company = "Not Specified";
        this.designation = "Not Specified";
    }

    public BusinessContact(String name) {
        super(name);
        this.company = "Not Specified";
        this.designation = "Not Specified";
    }

    public BusinessContact(String name, String phoneNumber) {
        super(name, phoneNumber);
        this.company = "Not Specified";
        this.designation = "Not Specified";
    }

    public BusinessContact(String name, String phoneNumber, String email, 
                           String relationship, String company, String designation) {
        super(name, phoneNumber, email, relationship);
        setCompany(company);
        setDesignation(designation);
    }

    // Polymorphic Method Overriding
    @Override
    public void displayContact() {
        super.displayContact(true);
        System.out.printf("Company: %-25s\n", this.company);
        System.out.printf("Designation: %-25s\n", this.designation);
        System.out.println("=====================================");
    }

    // Implementing Abstract Method
    @Override
    public String getContactType() {
        return "Business Contact";
    }

    // Setters
    public void setCompany(String company) {
        this.company = (company == null || company.trim().isEmpty()) 
            ? "Not Specified" 
            : company.trim();
    }

    public void setDesignation(String designation) {
        this.designation = (designation == null || designation.trim().isEmpty()) 
            ? "Not Specified" 
            : designation.trim();
    }

    // Getters
    public String getCompany() { return company; }
    public String getDesignation() { return designation; }
}

public class ContactPolymorphismDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Polymorphic Array of Contacts
        ArrayList<Contact> contacts = new ArrayList<>();

        // Creating Personal Contact
        System.out.println("\nCreating a PersonalContact:");
        PersonalContact personalContact = createPersonalContact(scanner);
        contacts.add(personalContact);

        // Creating Business Contact
        System.out.println("\nCreating a BusinessContact:");
        BusinessContact businessContact = createBusinessContact(scanner);
        contacts.add(businessContact);

        // Polymorphic Display
        System.out.println("\nContacts Information:");
        for (Contact contact : contacts) {
            System.out.println("Contact Type: " + contact.getContactType());
            contact.displayContact();
        }

        scanner.close();
    }

    // Helper method to create Personal Contact
    private static PersonalContact createPersonalContact(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Phone Number (10 digits): ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Relationship: ");
        String relationship = scanner.nextLine();

        return new PersonalContact(name, phoneNumber, email, relationship);
    }

    // Helper method to create Business Contact
    private static BusinessContact createBusinessContact(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Phone Number (10 digits): ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Relationship: ");
        String relationship = scanner.nextLine();

        System.out.print("Enter Company: ");
        String company = scanner.nextLine();

        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine();

        return new BusinessContact(name, phoneNumber, email, relationship, company, designation);
    }
}