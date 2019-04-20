package userdetails;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<EmployeeEntry, Integer> {
    List<EmployeeEntry> findByEmail(String email);
    @Transactional
    Long deleteByEmail(String email);
}
