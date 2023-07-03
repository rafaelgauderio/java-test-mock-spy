package com.rafaeldeluca.Producttestingmockspy.controllers;

import com.rafaeldeluca.Producttestingmockspy.dto.VegetableDTO;
import com.rafaeldeluca.Producttestingmockspy.services.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/vegetables")
public class VegetalbeController {

    @Autowired
    private VegetableService service;

    @PostMapping
    public ResponseEntity<VegetableDTO> insert(@RequestBody VegetableDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }

    @PutMapping(value="/{id}")
    public ResponseEntity<VegetableDTO> update(@PathVariable Long id, @RequestBody VegetableDTO dto) {
        dto = service.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }

}
