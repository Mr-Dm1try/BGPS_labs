package com.bgps.labs.controllers;

import com.bgps.labs.daos.JournalJdbc;
import com.bgps.labs.models.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JournalController {
    private final JournalJdbc _jrnl;

    public JournalController(JournalJdbc mark) {
        _jrnl = mark;
    }

    @GetMapping("/journal/{id}")
    public JournalEntry getEntryById(@PathVariable int id) {
        return _jrnl.get(id);
    }

    @GetMapping("/journal")
    public List<JournalEntry> getJournal(){
        return _jrnl.getAll();
    }

    @PostMapping("/journal/new")
    public int addNewEntry(@RequestBody JournalEntry sg){
        return _jrnl.add(sg);
    }

    @PostMapping("/journal/update")
    public int updateEntry(@RequestBody JournalEntry sg){
        return _jrnl.update(sg);
    }

    @DeleteMapping("/journal/delete/{id}")
    public int deleteEntryById(@PathVariable int id){
        return _jrnl.delete(id);
    }
}
