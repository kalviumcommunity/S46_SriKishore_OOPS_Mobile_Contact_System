import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

// Abstract Base Class with Virtual Methods
abstract class Contact implements Comparable<Contact> {
    // Protected attributes for inheritance
    protected String name;
    protected String phoneNumber;
    protected String email;

    // Abstract method to be implemented by child classes
    public abstract String getContactType();

    // Virtual method with default implementation
    public String formatContactInfo() {
        return String.format("Name: %s, Phone: %s", name, phoneNumber);
    }

    // Another virtual method to be overridden
    public boolean validateContact() {
        return isValidName() && isValidPhoneNumber() && isValidEmail();
    }

    // Concrete method for name validation
    protected boolean isValidName() {
        return name != null && !name.trim().isEmpty() && name.length() >= 2;
    }

    // Concrete method for phone number validation
    protected boolean isValidPhoneNumber() {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }

    // Concrete method for email validation
    protected boolean isValidEmail() {
        if (email == null) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    // Abstract method for display
    public abstract void displayContactDetails();

    // Constructor
    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Implementing Comparable interface for sorting contacts
    @Override
    public int compareTo(Contact other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

// Abstract Intermediate Class
abstract class BasePersonalContact extends Contact {
    // Additional protected attributes
    protected String relationship;

    // Constructor chaining
    public BasePersonalContact(String name, String phoneNumber, String email, String relationship) {
        super(name, phoneNumber, email);
        this.relationship = relationship;
    }

    // Virtual method with default implementation
    public String getRelationshipStatus() {
        return "Standard " + relationship + " Contact";
    }

    // Concrete method for proximity with default implementation
    public double calculateProximity() {
        // Default implementation
        return relationship.equals("Family") ? 1.0 : 0.5;
    }
}

// Concrete Personal Contact Class
class PersonalContact extends BasePersonalContact {
    // Additional attributes
    private String birthDate;

    // Constructor
    public PersonalContact(String name, String phoneNumber, String email, 
                           String relationship, String birthDate) {
        super(name, phoneNumber, email, relationship);
        this.birthDate = birthDate;
    }

    // Implementing abstract methods
    @Override
    public String getContactType() {
        return "Personal Contact";
    }

    @Override
    public void displayContactDetails() {
        System.out.println("Personal Contact Details:");
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Relationship: " + relationship);
        System.out.println("Birth Date: " + birthDate);
    }

    // Overriding virtual method
    @Override
    public String formatContactInfo() {
        return super.formatContactInfo() + ", Relationship: " + relationship;
    }

    // Overriding proximity calculation
    @Override
    public double calculateProximity() {
        // More specific proximity calculation for personal contact
        return relationship.equals("Family") ? 1.0 : 
               relationship.equals("Close Friend") ? 0.8 : 0.5;
    }
}

// Concrete Business Contact Class
class BusinessContact extends BasePersonalContact {
    // Additional business-specific attributes
    private String company;
    private String designation;

    // Constructor
    public BusinessContact(String name, String phoneNumber, String email, 
                           String relationship, String company, String designation) {
        super(name, phoneNumber, email, relationship);
        this.company = company;
        this.designation = designation;
    }

    // Implementing abstract methods
    @Override
    public String getContactType() {
        return "Business Contact";
    }

    @Override
    public void displayContactDetails() {
        System.out.println("Business Contact Details:");
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Relationship: " + relationship);
        System.out.println("Company: " + company);
        System.out.println("Designation: " + designation);
    }

    // Overriding virtual method
    @Override
    public String formatContactInfo() {
        return super.formatContactInfo() + ", Company: " + company;
    }

    // Overriding proximity calculation
    @Override
    public double calculateProximity() {
        // More specific proximity calculation for business contact
        return !company.isEmpty() ? 0.7 : 
               designation != null && !designation.isEmpty() ? 0.6 : 0.3;
    }
}

public class ContactManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        // Creating Personal Contact
        System.out.println("Enter Personal Contact Details:");
        PersonalContact personalContact = createPersonalContact(scanner);
        contacts.add(personalContact);

        // Creating Business Contact
        System.out.println("Enter Business Contact Details:");
        BusinessContact businessContact = createBusinessContact(scanner);
        contacts.add(businessContact);

        // Demonstrating polymorphic behavior
        System.out.println("\n--- Contact Details ---");
        for (Contact contact : contacts) {
            // Virtual method calls
            System.out.println("Contact Type: " + contact.getContactType());
            System.out.println("Formatted Info: " + contact.formatContactInfo());
            
            // Proximity calculation
            if (contact instanceof BasePersonalContact) {
                BasePersonalContact personalContactInstance = (BasePersonalContact) contact;
                System.out.println("Proximity Score: " + personalContactInstance.calculateProximity());
            }
            
            // Abstract method call
            contact.displayContactDetails();
            
            // Validation
            System.out.println("Contact Validation: " + 
                (contact.validateContact() ? "Valid" : "Invalid"));
            System.out.println("--------------------");
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

        System.out.print("Enter Birth Date: ");
        String birthDate = scanner.nextLine();

        return new PersonalContact(name, phoneNumber, email, relationship, birthDate);
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