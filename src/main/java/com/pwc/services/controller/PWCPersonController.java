package com.pwc.services.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.pwc.services.domain.PWCPerson;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PWCPersonController {

    private final Logger LOG = LoggerFactory.getLogger(PWCPersonController.class);
    private final List<PWCPerson> objs = new ArrayList<>();

    @GetMapping
    public List<PWCPerson> findAll() {
        return objs;
    }

    @GetMapping("/{id}")
    public PWCPerson findById(@PathVariable("id") Long id) {
        PWCPerson obj = objs.stream().filter(it -> it.getId().equals(id))
                .findFirst()
                .orElseThrow();
        LOG.info("Found: {}", obj.getId());
        return obj;
    }

    @PostMapping
    public PWCPerson add(@RequestBody PWCPerson obj) {
        obj.setId((long) (objs.size() + 1));
        LOG.info("Added: {}", obj);
        objs.add(obj);
        return obj;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        PWCPerson obj = objs.stream().filter(it -> it.getId().equals(id)).findFirst().orElseThrow();
        objs.remove(obj);
        LOG.info("Removed: {}", id);
    }

    @PutMapping
    public void update(@RequestBody PWCPerson obj) {
        PWCPerson objTmp = objs.stream()
                .filter(it -> it.getId().equals(obj.getId()))
                .findFirst()
                .orElseThrow();
        objs.set(objs.indexOf(objTmp), obj);
        LOG.info("Updated: {}", obj.getId());
    }

}