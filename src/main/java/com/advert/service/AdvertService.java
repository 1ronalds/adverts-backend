package com.advert.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import com.advert.model.AdvertDto;
import com.advert.model.AdvertMinimalDto;
import com.advert.model.AdvertUploadDto;
import com.advert.repository.AdvertRepository;
import com.advert.repository.UserRepository;
import com.advert.repository.model.AdvertEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class AdvertService {

    AdvertRepository advertRepository;
    UserRepository userRepository;
    public List<AdvertMinimalDto> getAdvertList() {
        return advertRepository.findAll()
                .stream()
                .map((advertEntity) -> new AdvertMinimalDto(advertEntity.getAdvertID(), advertEntity.getTitle(), advertEntity.getPrice(), advertEntity.getImgLocation()))
                .collect(Collectors.toList());
    }

    public List<AdvertMinimalDto> getAllAdvertsByUsername(String username){
        Long userId = userRepository.findByUsername(username).getUserID();
        return advertRepository.findAllByUserID(userId)
                .stream()
                .map((advertEntity) -> new AdvertMinimalDto(advertEntity.getAdvertID(), advertEntity.getTitle(), advertEntity.getPrice(), advertEntity.getImgLocation()))
                .collect(Collectors.toList());
    }

    public AdvertDto getAdvertById(Long id){
        return advertRepository.findById(id)
                .map((advertEntity) -> new AdvertDto(advertEntity.getAdvertID(), advertEntity.getTitle(), advertEntity.getDescription(), advertEntity.getPrice(), advertEntity.getImgLocation(), userRepository.findById(advertEntity.getUserId()).get().getUsername())).get();

    }

    public void deleteAdvertById(Long id, String username) {
        if(userRepository.findByUsername(username).getUserID().equals(advertRepository.findById(id).get().getUserId())) {
            advertRepository.deleteById(id);
        } else {
            throw new RuntimeException("You are not the owner of this advert");
        }
    }

    public void createNewAdvert(AdvertUploadDto advertUploadDto, String username) {
        if(userRepository.findByUsername(username).getUserID().equals(advertUploadDto.getUserID())) {
            byte[] decodedBytes = Base64.getDecoder().decode(advertUploadDto.getImgData());
            Path destinationFile = Paths.get("/images", advertUploadDto.getImgName());
            try {
                Files.write(destinationFile, decodedBytes);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store image", e);
            }
            String imgLocation = destinationFile.toString();
            advertRepository.save(new AdvertEntity(null, advertUploadDto.getTitle(), advertUploadDto.getDescription(), advertUploadDto.getPrice(), imgLocation, advertUploadDto.getUserID()));
        } else {
            throw new RuntimeException("You are not the owner of this advert");
        }
    }

    public void editAdvert(AdvertUploadDto advertUploadDto, String username, Long advertId) {
        if(userRepository.findByUsername(username).getUserID().equals(advertUploadDto.getUserID())) {
            byte[] decodedBytes = Base64.getDecoder().decode(advertUploadDto.getImgData());
            Path destinationFile = Paths.get("/images", advertUploadDto.getImgName());
            try {
                Files.write(destinationFile, decodedBytes);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store image", e);
            }
            String imgLocation = destinationFile.toString();
            advertRepository.save(new AdvertEntity(advertId, advertUploadDto.getTitle(), advertUploadDto.getDescription(), advertUploadDto.getPrice(), imgLocation, advertUploadDto.getUserID()));
        } else {
            throw new RuntimeException("You are not the owner of this advert");
        }
    }
}
