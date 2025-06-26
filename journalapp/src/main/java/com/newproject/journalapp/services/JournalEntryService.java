package com.newproject.journalapp.services;

import com.newproject.journalapp.entity.JournalEntry;
import com.newproject.journalapp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry myEntry){
        if(myEntry.getId()>=0L){
            journalEntryRepository.save(myEntry);
        }
        else{
            System.out.println("Already an entry for the given id , try changing the id");
        }
    }

    public List<JournalEntry> getEntry(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(Long id){
        return journalEntryRepository.findById(id);
    }

    public String deleteEntryById(Long id){
        journalEntryRepository.deleteById(id);
        return "deleted successfully";
    }


}
