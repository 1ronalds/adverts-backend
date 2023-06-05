package com.advert.service;

import com.advert.model.ApplicationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    public List<ApplicationDto> getAllApplicationsCreatedBy(String username) {
        return null;
    }

    public List<ApplicationDto> getAllApplicationsForOthers(String username) {
        return null;
    }

    public void createNewApplication(String username, ApplicationDto applicationDto) {

    }

    public void deleteApplication(String username, Long application_id) {

    }
}
