package com.advert.controller;

import java.util.List;

import com.advert.model.AdvertUploadDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.advert.model.AdvertDto;
import com.advert.model.AdvertMinimalDto;
import com.advert.service.AdvertService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/advert")
public class AdvertController {
    private final AdvertService advertService;

    @GetMapping("/view/all")
    public ResponseEntity<List<AdvertMinimalDto>> getAdvertList(){
        return ResponseEntity.ok(advertService.getAdvertList());
    }

    @PreAuthorize("(#username == authentication.principal)")
    @GetMapping("/view/user/{username}")
    public ResponseEntity<List<AdvertMinimalDto>> getAllMyAdverts(@PathVariable String username){
        return ResponseEntity.ok(advertService.getAllAdvertsByUsername(username));
    }

    @GetMapping("/view/advert/{id}")
    public ResponseEntity<AdvertDto> getAdvertById(@PathVariable Long id){
        return ResponseEntity.ok(advertService.getAdvertById(id));
    }

    @PreAuthorize("(#username == authentication.principal) or hasAuthority('admin')")
    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity<Void> deleteAdvertById(@PathVariable Long id, @PathVariable String username){
        advertService.deleteAdvertById(id, username);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("(#username == authentication.principal)")
    @PostMapping("/new/{username}")
    public ResponseEntity<Void> createNewAdvert(@Valid @RequestBody AdvertUploadDto advertUploadDto, @PathVariable String username){
        advertService.createNewAdvert(advertUploadDto, username);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("(#username == authentication.principal)")
    @PutMapping("/edit/{username}/{advertId}")
    public ResponseEntity<Void> editAdvert(@Valid @RequestBody AdvertUploadDto advertUploadDto, @PathVariable String username, @PathVariable Long advertId){
        advertService.editAdvert(advertUploadDto, username, advertId);
        return ResponseEntity.noContent().build();
    }


}
