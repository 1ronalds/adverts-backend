package com.advert.controller;

import java.util.List;

import com.advert.model.AdvertUploadDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.advert.model.AdvertDto;
import com.advert.model.AdvertMinimalDto;
import com.advert.service.AdvertService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/advert")
public class AdvertController {
    private final AdvertService advertService;

    @GetMapping("/view/all")
    public ResponseEntity<List<AdvertMinimalDto>> getAdvertList(){
        return ResponseEntity.ok(advertService.getAdvertList());
    }
    
    @GetMapping("/view/user/{username}")
    public ResponseEntity<List<AdvertMinimalDto>> getAllMyAdverts(@PathVariable String username){
        return ResponseEntity.ok(advertService.getAllAdvertsByUsername(username));
    }

    @GetMapping("/view/advert/{id}")
    public ResponseEntity<AdvertDto> getAdvertById(@PathVariable Long id){
        return ResponseEntity.ok(advertService.getAdvertById(id));
    }

    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity<Void> deleteAdvertById(@PathVariable Long id, @PathVariable String username){
        advertService.deleteAdvertById(id, username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/new/{username}")
    public ResponseEntity<Void> createNewAdvert(@RequestBody AdvertUploadDto advertUploadDto, @PathVariable String username){
        advertService.createNewAdvert(advertUploadDto, username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/edit/{username}/{id}")
    public ResponseEntity<Void> createNewAdvert(@RequestBody AdvertUploadDto advertUploadDto, @PathVariable String username, @PathVariable Long advertId){
        advertService.editAdvert(advertUploadDto, username, advertId);
        return ResponseEntity.noContent().build();
    }


}
