package br.com.vibbra.notify.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vibbra.notify.persistence.model.Application;
import br.com.vibbra.notify.persistence.repository.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<Application> getAllApplication() {
        return applicationRepository.findAll();
    }

    @Override
    public void saveApplication(Application application) {
        this.applicationRepository.save(application);
    }

    @Override
    public Application getApplicationById(long id) {
        Optional<Application> optional = applicationRepository.findById(id);
        Application application = null;
        if (optional.isPresent()) {
            application = optional.get();
        } else {
            throw new RuntimeException(" Application not found for id :: " + id);
        }
        return application;
    }

    @Override
    public void deleteApplicationById(long id) {
        this.applicationRepository.deleteById(id);
    }
}