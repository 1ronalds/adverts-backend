package com.advert.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<AdvertMinimalDto>> getAllAdvertsByUsername(@PathVariable String username){
        return ResponseEntity.ok(advertService.getAllAdvertsByUsername(username));
    }

    @GetMapping("/view/advert/{id}")
    public ResponseEntity<List<AdvertMinimalDto>> getAdvertById(@PathVariable Long id){
        return ResponseEntity.ok(advertService.getAdvertById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdvertById(@PathVariable Long id){
        advertService.deleteAdvertById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createNewAdvert(@RequestBody AdvertDto advertDto){
        advertService.createNewAdvert(advertDto);
        return ResponseEntity.noContent().build();
    }


}
