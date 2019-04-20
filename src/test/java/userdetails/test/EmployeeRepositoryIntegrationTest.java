package userdetails.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import userdetails.Application;
import userdetails.Employee;
import userdetails.EmployeeEntry;
import userdetails.EmployeeRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void returnsExistingEmployeeByEmail() {
        EmployeeEntry alex = new EmployeeEntry("Alex", "Freeman", "19.12.03", "ag@gmail.com");
        employeeRepository.save(alex);
        List<EmployeeEntry> foundEmployees = employeeRepository.findByEmail(alex.getEmail());
        assertThat(foundEmployees.size()).isEqualTo(1);
        EmployeeEntry found = foundEmployees.get(0);
        assertThat(found.getEmail()).isEqualTo(alex.getEmail());
    }

    @Test
    public void deletesEmployee() {
        EmployeeEntry alex = new EmployeeEntry("Alex", "Freeman", "19.12.03", "ag@gmail.com");
        employeeRepository.save(alex);
        List<EmployeeEntry> foundEmployees = employeeRepository.findByEmail(alex.getEmail());
        assertThat(foundEmployees.size()).isEqualTo(1);
        EmployeeEntry found = foundEmployees.get(0);
        assertThat(found.getEmail()).isEqualTo(alex.getEmail());
        employeeRepository.deleteByEmail(alex.getEmail());
        List<EmployeeEntry> deleted = employeeRepository.findByEmail(alex.getEmail());
        assertThat(deleted.size()).isEqualTo(0);
    }
}
