package com.newproject.journalapp.controller;

import com.newproject.journalapp.entity.JournalEntry;
import com.newproject.journalapp.services.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController // For telling Spring that this class will interact with the web and the endpoints will be created here
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    public JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry> journalEntry= journalEntryService.getEntry();
        if(journalEntry.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(journalEntry,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDateTime(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
        return new ResponseEntity<>("Entry Created",HttpStatus.CREATED);
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable Long myid){
        Optional<JournalEntry> journalEntry= journalEntryService.getEntryById(myid);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable Long myid){
        Optional<JournalEntry> journalEntry= journalEntryService.getEntryById(myid);
        if(journalEntry.isPresent()) {
            journalEntryService.deleteEntryById(myid);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Put/Updating Logic

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntryById(@PathVariable Long id,@RequestBody JournalEntry newEntry){
       JournalEntry old = journalEntryService.getEntryById(id).orElse(null);
       if(old!=null){
           old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
           old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
       }
       else{
           return new ResponseEntity<>("No Entry corresponding to the ID",HttpStatus.NOT_FOUND) ;
       }
        journalEntryService.saveEntry(old);
        return new ResponseEntity<>("Entry updated successfully",HttpStatus.OK) ;
    }


}
