package pl.warsaw.hackaton.nextgensalesservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.warsaw.hackaton.nextgensalesservice.model.ComponentCatalogResponse;
import pl.warsaw.hackaton.nextgensalesservice.service.ComponentCatalogService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ComponentCatalogController {

    private final ComponentCatalogService componentCatalogService;

    @GetMapping(path = "/component-catalog")
    public ResponseEntity<List<ComponentCatalogResponse>> getComponentCatalogs() {
        log.info("GET /component-catalog");
        return ResponseEntity.ok(componentCatalogService.getComponentCatalogs());
    }

}
