package userdetails;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PasswordsRepository extends CrudRepository<PasswordEntry, Integer> {
    List<PasswordEntry> findByEmail(String email);
    @Transactional
    Long deleteByEmail(String email);
}
