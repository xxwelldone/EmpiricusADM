package br.com.empiricus.EmpiricusADM.Repository;

import br.com.empiricus.EmpiricusADM.Model.Emails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Emails, Long> {


}
