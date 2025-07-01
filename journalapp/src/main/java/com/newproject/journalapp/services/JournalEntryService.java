package com.newproject.journalapp.services;

import com.newproject.journalapp.entity.JournalEntry;
import com.newproject.journalapp.entity.User;
import com.newproject.journalapp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
//Creating a bean
@Component
public class JournalEntryService {
    //Dependency Injection
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    //Business logics for Get,Post,Delete,Put

    //Post Logic
    public void saveEntry(JournalEntry myEntry, String username) {
        User user = userService.getUserByUsername(username).orElse(null);
            JournalEntry journalEntry = journalEntryRepository.save(myEntry);
            user.getJournalEntries().add(journalEntry);
            userService.saveUser(user);

    }
    public void saveEntry(JournalEntry myEntry){
        journalEntryRepository.save(myEntry);
    }

    //Get Logic
    public List<JournalEntry> getEntry() {
        return journalEntryRepository.findAll();
    }

    //Get Logic by Id
    public Optional<JournalEntry> getEntryById(String id) {
        return journalEntryRepository.findById(id);
    }

    //Delete Logic
    public String deleteEntryById(String id, String username) {
        User user = userService.getUserByUsername(username).orElse(null);
        Optional<JournalEntry> entry = journalEntryRepository.findById(id);
        if (entry.isPresent()) {
            user.getJournalEntries().removeIf(x-> x.getId().equals(id));
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
            return "deleted successfully";
        }
        else{
            return "No Entry Present";
        }
    }



}
