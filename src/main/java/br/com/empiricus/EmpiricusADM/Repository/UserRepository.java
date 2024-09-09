package br.com.empiricus.EmpiricusADM.Repository;

import br.com.empiricus.EmpiricusADM.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
