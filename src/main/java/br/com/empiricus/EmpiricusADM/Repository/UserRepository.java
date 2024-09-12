package br.com.empiricus.EmpiricusADM.Repository;

import br.com.empiricus.EmpiricusADM.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);
}
