package userdetails;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEE")
public class EmployeeEntry {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String dob;

    public EmployeeEntry(String name, String surname, String dob, String email) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
    }

    public EmployeeEntry() {}

    public EmployeeEntry(Employee e) {
        this.name = e.getName();
        this.surname = e.getSurname();
        this.dob = e.getDob();
        this.email = e.getEmail();
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EmployeeEntry)) return false;
        else {
            EmployeeEntry other = (EmployeeEntry) obj;
            boolean eqName = this.getName() == null ? other.getName() == null : this.getName().equals(other.getName());
            boolean eqSurname = this.getSurname() == null ? other.getSurname() == null : this.getSurname().equals(other.getSurname());
            boolean eqDob = this.getDob() == null ? other.getDob() == null : this.getDob().equals(other.getDob());
            boolean eqEmail = this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail());
            return eqName && eqSurname && eqDob && eqEmail;
        }
    }
}
