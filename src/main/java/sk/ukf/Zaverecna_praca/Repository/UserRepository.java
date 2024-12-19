package sk.ukf.Zaverecna_praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.Zaverecna_praca.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
