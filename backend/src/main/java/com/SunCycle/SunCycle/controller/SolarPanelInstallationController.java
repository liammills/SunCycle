package com.SunCycle.SunCycle.controller;

import com.SunCycle.SunCycle.dto.SolarPanelInstallationRequestDTO;
import com.SunCycle.SunCycle.dto.SolarPanelInstallationResponseDTO;
import com.SunCycle.SunCycle.dto.Status;
import com.SunCycle.SunCycle.model.SolarPanelInstallation;
import com.SunCycle.SunCycle.service.SolarPanelInstallationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/installations")
@CrossOrigin
public class SolarPanelInstallationController {

    @Autowired
    private SolarPanelInstallationService solarPanelInstallationService;

    @GetMapping("")
    public ResponseEntity<?> getInstallationsByUser(Authentication authentication) {
        List<SolarPanelInstallationResponseDTO> result = solarPanelInstallationService.getInstallationsByEmail(authentication.getName());

        if (result == null)
            return ResponseEntity.badRequest().body("This user doesn't have any associated solar panel installations.");

        return ResponseEntity.ok(solarPanelInstallationService.getInstallationsByEmail(authentication.getName()));
    }

    @GetMapping("/{installationId}")
    public ResponseEntity<?> getInstallationsById(Authentication authentication, @PathVariable int installationId) {
        SolarPanelInstallationResponseDTO result = solarPanelInstallationService.getInstallationById(authentication, installationId);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<?> createInstallation(@RequestBody SolarPanelInstallationRequestDTO dto) {
        SolarPanelInstallationResponseDTO result = solarPanelInstallationService.createSolarPanelInstallation(dto);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{installationId}")
    public ResponseEntity<?> updateInstallation(Authentication authentication, @PathVariable int installationId, @RequestBody SolarPanelInstallationRequestDTO dto) {
        SolarPanelInstallationResponseDTO result = solarPanelInstallationService.updateSolarPanelInstallation(authentication, installationId, dto);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{installationId}")
    public ResponseEntity<?> deleteInstallation(Authentication authentication, @PathVariable int installationId) {
        SolarPanelInstallationResponseDTO result = solarPanelInstallationService.deleteInstallation(authentication, installationId);

        if (result.getStatus() == Status.NOT_FOUND || result.getStatus() == Status.ERROR) {
            return ResponseEntity.badRequest().body(result.getMessage());
        }

        return ResponseEntity.ok(result);
    }
}
