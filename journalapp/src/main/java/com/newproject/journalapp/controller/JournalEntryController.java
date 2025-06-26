package com.newproject.journalapp.controller;

import com.newproject.journalapp.entity.JournalEntry;
import com.newproject.journalapp.services.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController // For telling Spring that this class will interact with the web and the endpoints will be created here
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    public JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getEntry();

    }

    @PostMapping
    public String createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDateTime(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
        return "Entry Created Successfully";
    }

    @GetMapping("/id/{myid}")
    public JournalEntry getJournalEntryById(@PathVariable Long myid){
        return journalEntryService.getEntryById(myid).orElse(null);
    }

    @DeleteMapping("/id/{myid}")
    public String deleteJournalEntryById(@PathVariable Long myid){
        journalEntryService.deleteEntryById(myid);
        return "Entry deleted Successfully";
    }

    //Put/Updating Logic

    @PutMapping("/id/{id}")
    public String updateEntryById(@PathVariable Long id,@RequestBody JournalEntry newEntry){
       JournalEntry old = journalEntryService.getEntryById(id).orElse(null);
       if(old!=null){
           old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
           old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
       }
       else{
           return "Entry not created";
       }
        journalEntryService.saveEntry(old);
        return "Entry updated successfully";
    }


}
