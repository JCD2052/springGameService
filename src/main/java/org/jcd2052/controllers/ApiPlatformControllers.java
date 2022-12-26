package org.jcd2052.controllers;

import org.jcd2052.models.Platform;
import org.jcd2052.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/platforms")
public class ApiPlatformControllers {
    private final PlatformService platformService;

    @Autowired
    public ApiPlatformControllers(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public Set<Platform> getAllPlatforms() {
        return platformService.getAll();
    }

    @GetMapping("/{id}")
    public Platform getPlatformById(@PathVariable int id) {
        return platformService.getById(id);
    }

}
