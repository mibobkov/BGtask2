package userdetails;


import javax.persistence.*;

@Entity
public class PasswordEntry {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private byte[] hash;
    private byte[] salt;

    public PasswordEntry() { }

    public PasswordEntry(String email, byte[] hash, byte[] salt) {
        this.email = email;
        this.hash = hash;
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getHash() {
        return hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}

