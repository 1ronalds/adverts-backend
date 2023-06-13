package com.advert.service;

import com.advert.model.AdvertMinimalDto;
import com.advert.model.ApplicationDto;
import com.advert.repository.ApplicationRepository;
import com.advert.repository.UserRepository;
import com.advert.repository.model.ApplicationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final AdvertService advertService;


    public List<ApplicationDto> getAllApplicationsCreatedBy(String username) {
        List<AdvertMinimalDto> myAdverts = advertService.getAllAdvertsByUsername(username);
        return myAdverts.stream()
                .map(advertMinimalDto -> new ApplicationDto(advertMinimalDto, advertService.getAdvertById(advertMinimalDto.getAdvertID()).getUserName()))
                .collect(Collectors.toList());
    }

    public List<ApplicationDto> getAllApplicationsForOthers(String username) {
        List<ApplicationEntity> applicationEntityList = applicationRepository.findAllByUserId(userRepository.findByUsername(username).get().getUserID());
        return applicationEntityList.stream()
                .map(applicationEntity -> new ApplicationDto(advertService.getAdvertMinimalDtoById(applicationEntity.getAdvertId()), username))
                .collect(Collectors.toList());
    }

    public void createNewApplication(String username, ApplicationDto applicationDto) {
        Long advertId = applicationDto.getAdvertMinimalDto().getAdvertID();
        System.out.println("advertId: " + advertId);
        applicationRepository.save(new ApplicationEntity(advertId, userRepository.findByUsername(username).get().getUserID()));
    }
    /*
    public void createNewApplication(String username, ApplicationDto applicationDto) {
        applicationRepository.save(new ApplicationEntity(applicationDto.getAdvertMinimalDto().getAdvertID(), userRepository.findByUsername(username).get().getUserID()));
    }*/

    public void deleteApplication(String username, Long application_id) {
        if(applicationRepository.findById(application_id).get().getUserId().equals(userRepository.findByUsername(username).get().getUserID())){
            applicationRepository.deleteById(application_id);
        } else {
            throw new RuntimeException("You are not the owner of this application");
        }
    }
}
