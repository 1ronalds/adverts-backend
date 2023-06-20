package com.advert.controller;

import com.advert.model.ApplicationDto;
import com.advert.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PreAuthorize("(#username == authentication.principal)")
    @GetMapping("/formine/{username}")
    public ResponseEntity<List<ApplicationDto>> getAllApplicationsToMyAdvertisments(@PathVariable String username){
        return ResponseEntity.ok(applicationService.getAllApplicationsCreatedFor(username));
    }

    @PreAuthorize("(#username == authentication.principal)")
    @GetMapping("/forothers/{username}")
    public ResponseEntity<List<ApplicationDto>> getAllMyApplicationsToOtherAdvertisements(@PathVariable String username){
        return ResponseEntity.ok(applicationService.getAllApplicationsForOthers(username));
    }

    @PreAuthorize("(#username == authentication.principal)")
    @PostMapping("/mine/{username}")
    public ResponseEntity<Void> createNewApplication(@PathVariable String username, @RequestBody ApplicationDto applicationDto){
        applicationService.createNewApplication(username, applicationDto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("(#username == authentication.principal)")
    @DeleteMapping("/mine/{username}/{application_id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable String username, @PathVariable Long application_id){
        applicationService.deleteApplication(username, application_id);
        return ResponseEntity.noContent().build();
    }

    
}
