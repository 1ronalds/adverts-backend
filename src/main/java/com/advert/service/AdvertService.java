package com.advert.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import com.advert.handler.InvalidDataException;
import com.advert.model.AdvertDto;
import com.advert.model.AdvertMinimalDto;
import com.advert.model.AdvertUploadDto;
import com.advert.repository.AdvertRepository;
import com.advert.repository.UserRepository;
import com.advert.repository.model.AdvertEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final UserRepository userRepository;

    public List<AdvertMinimalDto> getAdvertList() {
        System.out.println("hello");
        System.out.println(advertRepository.findAll());
        return advertRepository.findAll()
                .stream()
                .peek(advertEntity -> log.info("Processing advert: {}", advertEntity)) // This will log each advertEntity
                .map((advertEntity) -> new AdvertMinimalDto(advertEntity.getAdvertID(), advertEntity.getTitle(), advertEntity.getPrice(), advertEntity.getImgLocation()))
                .collect(Collectors.toList());
    }

    public List<AdvertMinimalDto> getAllAdvertsByUsername(String username) {
        Long userId = userRepository.findByUsername(username).get().getUserID();
        return advertRepository.findAllByUserId(userId)
                .stream()
                .map((advertEntity) -> new AdvertMinimalDto(advertEntity.getAdvertID(), advertEntity.getTitle(), advertEntity.getPrice(), advertEntity.getImgLocation()))
                .collect(Collectors.toList());
    }

    public AdvertDto getAdvertById(Long id) {
        return advertRepository.findById(id)
                .map((advertEntity) -> new AdvertDto(advertEntity.getAdvertID(), advertEntity.getTitle(), advertEntity.getDescription(), advertEntity.getPrice(), advertEntity.getImgLocation(), userRepository.findById(advertEntity.getUserId()).get().getUsername())).get();

    }


    public void deleteAdvertById(Long id, String username) {
        if (userRepository.findByUsername(username).get().getUserID().equals(advertRepository.findById(id).get().getUserId())) {
            advertRepository.deleteById(id);
        } else {
            throw new InvalidDataException();
        }
    }

    public void createNewAdvert(AdvertUploadDto advertUploadDto, String username) {
        Long userId = userRepository.findByUsername(username).get().getUserID();
        byte[] decodedBytes = Base64.getDecoder().decode(advertUploadDto.getImgData());
        Path destinationFile = Paths.get("images", advertUploadDto.getImgName());
        try {
            Files.write(destinationFile, decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        String imgLocation = destinationFile.toString();
        advertRepository.save(new AdvertEntity(null, advertUploadDto.getTitle(), advertUploadDto.getDescription(), advertUploadDto.getPrice(), imgLocation, userId));
    }

    public void editAdvert(AdvertUploadDto advertUploadDto, String username, Long advertId) {
        Long userId = userRepository.findByUsername(username).get().getUserID();
        byte[] decodedBytes = Base64.getDecoder().decode(advertUploadDto.getImgData());
        Path destinationFile = Paths.get("/images", advertUploadDto.getImgName());
        try {
            Files.write(destinationFile, decodedBytes);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        String imgLocation = destinationFile.toString();
        advertRepository.save(new AdvertEntity(advertId, advertUploadDto.getTitle(), advertUploadDto.getDescription(), advertUploadDto.getPrice(), imgLocation, userId));
    }

    public AdvertMinimalDto getAdvertMinimalDtoById(Long id) {
        return advertRepository.findById(id)
                .map((advertEntity) -> new AdvertMinimalDto(advertEntity.getAdvertID(), advertEntity.getTitle(), advertEntity.getPrice(), advertEntity.getImgLocation())).get();
    }
}



