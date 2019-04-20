package userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;

@RestController
public class UserDetailsController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordsRepository passwordsRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings!";
    }

    private PasswordEntry generatePE(String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return new PasswordEntry(email, hash, salt);
    }


    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee employee) {
        try {
            employeeRepository.save(new EmployeeEntry(employee));
            passwordsRepository.save(generatePE(employee.getEmail(), employee.getPassword()));
            System.out.println("Added employee");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerErrorException("Failed to add user");
        }
    }

    @DeleteMapping("/delete")
    public Long deleteEmployee(@RequestBody String email) {
        Long deleted = employeeRepository.deleteByEmail(email);
        Long deletedPassword = passwordsRepository.deleteByEmail(email);
        return deleted;
    }

    @GetMapping("/get")
    public EmployeeEntry getEmployee(@RequestBody String email) {
        System.out.println(email);
        List<EmployeeEntry> ee = employeeRepository.findByEmail(email);
        System.out.println(ee.size());
        if (ee.size() == 0) return null;
        else return ee.get(0);
    }
}

