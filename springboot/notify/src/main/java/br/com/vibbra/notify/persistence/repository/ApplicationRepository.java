package br.com.vibbra.notify.persistence.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.com.vibbra.notify.persistence.model.Application;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    List<Application> findByName(String name);

    List<Application> findAll();
}
