import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

// Abstract Base Class with Virtual Methods
abstract class Contact implements Comparable<Contact> {
    protected String name;
    protected String phoneNumber;
    protected String email;

    public abstract String getContactType();
    public String formatContactInfo() {
        return String.format("Name: %s, Phone: %s", name, phoneNumber);
    }
    public boolean validateContact() {
        return isValidName() && isValidPhoneNumber() && isValidEmail();
    }
    protected boolean isValidName() {
        return name != null && !name.trim().isEmpty() && name.length() >= 2;
    }
    protected boolean isValidPhoneNumber() {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }
    protected boolean isValidEmail() {
        if (email == null) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
    public abstract void displayContactDetails();
    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    @Override
    public int compareTo(Contact other) {
        return this.name.compareToIgnoreCase(other.name);
    }
}

// Abstract Intermediate Class
abstract class BasePersonalContact extends Contact {
    protected String relationship;

    public BasePersonalContact(String name, String phoneNumber, String email, String relationship) {
        super(name, phoneNumber, email);
        this.relationship = relationship;
    }
    public String getRelationshipStatus() {
        return "Standard " + relationship + " Contact";
    }
    public double calculateProximity() {
        return relationship.equals("Family") ? 1.0 : 0.5;
    }
}

// Concrete Personal Contact Class
class PersonalContact extends BasePersonalContact {
    private String birthDate;

    public PersonalContact(String name, String phoneNumber, String email, String relationship, String birthDate) {
        super(name, phoneNumber, email, relationship);
        this.birthDate = birthDate;
    }
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
    @Override
    public String formatContactInfo() {
        return super.formatContactInfo() + ", Relationship: " + relationship;
    }
    @Override
    public double calculateProximity() {
        return relationship.equals("Family") ? 1.0 : 
               relationship.equals("Close Friend") ? 0.8 : 0.5;
    }
}

// Concrete Business Contact Class
class BusinessContact extends BasePersonalContact {
    private String company;
    private String designation;

    public BusinessContact(String name, String phoneNumber, String email, 
                           String relationship, String company, String designation) {
        super(name, phoneNumber, email, relationship);
        this.company = company;
        this.designation = designation;
    }
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
    @Override
    public String formatContactInfo() {
        return super.formatContactInfo() + ", Company: " + company;
    }
    @Override
    public double calculateProximity() {
        return !company.isEmpty() ? 0.7 : 
               designation != null && !designation.isEmpty() ? 0.6 : 0.3;
    }
}

// Factory for Creating Contacts (SRP)
class ContactFactory {
    public static PersonalContact createPersonalContact(Scanner scanner) {
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
    public static BusinessContact createBusinessContact(Scanner scanner) {
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

// Main Class
public class ContactManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        System.out.println("Enter Personal Contact Details:");
        PersonalContact personalContact = ContactFactory.createPersonalContact(scanner);
        contacts.add(personalContact);

        System.out.println("Enter Business Contact Details:");
        BusinessContact businessContact = ContactFactory.createBusinessContact(scanner);
        contacts.add(businessContact);

        System.out.println("\n--- Contact Details ---");
        for (Contact contact : contacts) {
            System.out.println("Contact Type: " + contact.getContactType());
            System.out.println("Formatted Info: " + contact.formatContactInfo());
            if (contact instanceof BasePersonalContact) {
                System.out.println("Proximity Score: " + ((BasePersonalContact) contact).calculateProximity());
            }
            contact.displayContactDetails();
            System.out.println("Contact Validation: " + 
                (contact.validateContact() ? "Valid" : "Invalid"));
            System.out.println("--------------------");
        }
        scanner.close();
    }
}
