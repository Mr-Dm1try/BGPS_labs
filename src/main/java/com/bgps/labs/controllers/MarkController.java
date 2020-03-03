package com.bgps.labs.controllers;

import com.bgps.labs.daos.MarkJdbc;
import com.bgps.labs.models.Mark;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkController {
    private final MarkJdbc _mark;

    public MarkController(MarkJdbc mark) {
        _mark = mark;
    }

    @GetMapping("/marks/{id}")
    public Mark getMarkById(@PathVariable int id){
        return _mark.get(id);
    }
}
