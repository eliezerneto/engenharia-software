package br.com.vibbra.notify.service;

import java.util.List;

import br.com.vibbra.notify.persistence.model.Application;

public interface ApplicationService {
    List<Application> getAllApplication();

    void saveApplication(Application application);

    Application getApplicationById(long id);

    void deleteApplicationById(long id);
}