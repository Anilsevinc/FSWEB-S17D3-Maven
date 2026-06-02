package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/workintech/kangaroos")
public class KangarooController {

    private Map<Long, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public Collection<Kangaroo> findAll() {
        return kangaroos.values();
    }

    @GetMapping("/{id}")
    public Kangaroo findById(@PathVariable Long id) {

        if (!kangaroos.containsKey(id)) {
            throw new ZooException(
                    "Kangaroo not found with id: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo save(@RequestBody Kangaroo kangaroo) {
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable Long id,
                           @RequestBody Kangaroo kangaroo) {

        if (!kangaroos.containsKey(id)) {
            throw new ZooException(
                    "Kangaroo not found with id: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        kangaroos.put(id, kangaroo);
        return kangaroo;

    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable Long id) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException(
                    "Kangaroo not found with id: " + id,
                    HttpStatus.NOT_FOUND
            );
        }
        return kangaroos.remove(id);
    }
}