package com.advert.service;

import com.advert.model.AdvertMinimalDto;
import com.advert.model.ApplicationDto;
import com.advert.repository.ApplicationRepository;
import com.advert.repository.UserRepository;
import com.advert.repository.model.ApplicationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final AdvertService advertService;


    public List<ApplicationDto> getAllApplicationsCreatedFor(String username) {
        List<AdvertMinimalDto> myAdverts = advertService.getAllAdvertsByUsername(username);
        return myAdverts.stream()
                .map(advertMinimalDto -> applicationRepository.findAllByAdvertId(advertMinimalDto.getAdvertID()))
                .flatMap(List::stream)
                .map(applicationEntity -> new ApplicationDto(
                        applicationEntity.getApplicationId(),
                        advertService.getAdvertMinimalDtoById(applicationEntity.getAdvertId()),
                        userRepository.findById(applicationEntity.getUserId()).get().getUsername()))
                .collect(Collectors.toList());
    }
    public List<ApplicationDto> getAllApplicationsForOthers(String username) {
        List<ApplicationEntity> applicationEntityList = applicationRepository.findAllByUserId(userRepository.findByUsername(username).get().getUserID());
        return applicationEntityList
                .stream()
                .map(applicationEntity -> new ApplicationDto(applicationEntity.getApplicationId(), advertService.getAdvertMinimalDtoById(applicationEntity.getAdvertId()), userRepository.findById(applicationEntity.getUserId()).get().getUsername()))
                .collect(Collectors.toList());
    }

    public void createNewApplication(String username, ApplicationDto applicationDto) {
        Long advertId = applicationDto.getAdvertMinimalDto().getAdvertID();
        applicationRepository.save(new ApplicationEntity(null, advertId, userRepository.findByUsername(username).get().getUserID()));
    }

    public void deleteApplication(String username, Long application_id) {
        if(applicationRepository.findById(application_id).get().getUserId().equals(userRepository.findByUsername(username).get().getUserID())){
            applicationRepository.deleteById(application_id);
        } else {
            throw new RuntimeException("You are not the owner of this application");
        }
    }
}
