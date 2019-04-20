package userdetails;

public class Employee {
    private String email;
    private String name;
    private String surname;
    private String dob;
    private String password;

    public Employee(String name, String surname, String dob, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public String getDob() {
        return dob;
    }

    public String getPassword() {
        return password;
    }
}
