import java.util.Scanner;
import java.util.regex.Pattern;

// Base Class
class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    // Default constructor
    public Contact() {
        this.name = "Unknown";
        this.phoneNumber = "Not Provided";
        this.email = "Not Provided";
        System.out.println("Default Constructor Called: Contact initialized with default values.");
    }

    // Parameterized constructor with validation
    public Contact(String name, String phoneNumber, String email) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        System.out.println("Parameterized Constructor Called: Contact initialized with provided values.");
    }

    // Setter with validation for name
    public void setName(String name) {
        this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name.trim();
    }

    // Setter with validation for phone number
    public void setPhoneNumber(String phoneNumber) {
        // Basic phone number validation (10 digits)
        this.phoneNumber = (isValidPhoneNumber(phoneNumber)) ? phoneNumber : "Not Provided";
    }

    // Setter with validation for email
    public void setEmail(String email) {
        this.email = (isValidEmail(email)) ? email : "Not Provided";
    }

    // Phone number validation method
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }

    // Email validation method
    private boolean isValidEmail(String email) {
        if (email == null) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    // Public method to display contact details
    public void displayContact() {
        System.out.println("=====================================");
        System.out.println("Contact Details:");
        System.out.printf("Name: %-25s\n", this.name);
        System.out.printf("Phone Number: %-25s\n", this.phoneNumber);
        System.out.printf("Email: %-25s\n", this.email);
        System.out.println("=====================================");
    }

    // Getters for all attributes
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}

// Derived Class 1: Represents a Personal Contact
class PersonalContact extends Contact {
    private String relationship;

    // Default constructor
    public PersonalContact() {
        super(); // Call the default constructor of the base class
        this.relationship = "Not Specified";
        System.out.println("Default Constructor Called: PersonalContact initialized with default values.");
    }

    // Parameterized constructor
    public PersonalContact(String name, String phoneNumber, String email, String relationship) {
        super(name, phoneNumber, email); // Call the parameterized constructor of the base class
        setRelationship(relationship);
        System.out.println("Parameterized Constructor Called: PersonalContact initialized with provided values.");
    }

    // Setter with validation for relationship
    public void setRelationship(String relationship) {
        this.relationship = (relationship == null || relationship.trim().isEmpty()) 
            ? "Not Specified" 
            : relationship.trim();
    }

    // Override the display method to include relationship
    @Override
    public void displayContact() {
        super.displayContact(); // Call the base class method
        System.out.printf("Relationship: %-25s\n", this.relationship);
        System.out.println("=====================================");
    }

    // Getter for relationship
    public String getRelationship() {
        return relationship;
    }
}

// Derived Class 2: Represents a Business Contact (Multilevel Inheritance)
class BusinessContact extends PersonalContact {
    private String company;
    private String designation;

    // Default constructor
    public BusinessContact() {
        super(); // Call the default constructor of the parent class
        this.company = "Not Specified";
        this.designation = "Not Specified";
        System.out.println("Default Constructor Called: BusinessContact initialized with default values.");
    }

    // Parameterized constructor
    public BusinessContact(String name, String phoneNumber, String email, 
                           String relationship, String company, String designation) {
        super(name, phoneNumber, email, relationship); // Call the parameterized constructor of the parent class
        setCompany(company);
        setDesignation(designation);
        System.out.println("Parameterized Constructor Called: BusinessContact initialized with provided values.");
    }

    // Setter with validation for company
    public void setCompany(String company) {
        this.company = (company == null || company.trim().isEmpty()) 
            ? "Not Specified" 
            : company.trim();
    }

    // Setter with validation for designation
    public void setDesignation(String designation) {
        this.designation = (designation == null || designation.trim().isEmpty()) 
            ? "Not Specified" 
            : designation.trim();
    }

    // Override the display method to include company and designation
    @Override
    public void displayContact() {
        super.displayContact(); // Call the parent class method
        System.out.printf("Company: %-25s\n", this.company);
        System.out.printf("Designation: %-25s\n", this.designation);
        System.out.println("=====================================");
    }

    // Getters for company and designation
    public String getCompany() {
        return company;
    }

    public String getDesignation() {
        return designation;
    }
}

public class ContactApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demonstrating Single Inheritance with PersonalContact
        System.out.println("\nCreating a PersonalContact using the Parameterized Constructor:");
        PersonalContact personalContact = createPersonalContact(scanner);
        personalContact.displayContact();

        // Demonstrating Multilevel Inheritance with BusinessContact
        System.out.println("\nCreating a BusinessContact using the Parameterized Constructor:");
        BusinessContact businessContact = createBusinessContact(scanner);
        businessContact.displayContact();

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