package com.newproject.journalapp.controller;

import com.newproject.journalapp.entity.JournalEntry;
import com.newproject.journalapp.entity.User;
import com.newproject.journalapp.services.JournalEntryService;
import com.newproject.journalapp.services.UserService;
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
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAll(@PathVariable String username){
        User user = userService.getUserByUsername(username).orElse(null);
        List<JournalEntry> journalEntry= user.getJournalEntries();
        if(journalEntry.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(journalEntry,HttpStatus.OK);
    }

    @PostMapping("{username}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String username){
        myEntry.setDateTime(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry,username);
        return new ResponseEntity<>("Entry Created",HttpStatus.CREATED);
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable String myid){
        Optional<JournalEntry> journalEntry= journalEntryService.getEntryById(myid);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{username}/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String myid,@PathVariable String username){
        Optional<JournalEntry> journalEntry= journalEntryService.getEntryById(myid);
        if(journalEntry.isPresent()) {
            journalEntryService.deleteEntryById(myid,username);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Put/Updating Logic

    @PutMapping("/id/{username}/{entryid}")
    public ResponseEntity<?> updateEntryById(@PathVariable String entryid,@RequestBody JournalEntry newEntry,@PathVariable String username){
       JournalEntry old = journalEntryService.getEntryById(entryid).orElse(null);
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
