package com.newproject.journalapp.services;

import com.newproject.journalapp.entity.JournalEntry;
import com.newproject.journalapp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
//Creating a bean
@Component
public class JournalEntryService {
    //Dependency Injection
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    //Business logics for Get,Post,Delete,Put

    //Post Logic
    public void saveEntry(JournalEntry myEntry){
        if(myEntry.getId()>=0L){
            journalEntryRepository.save(myEntry);
        }
        else{
            System.out.println("Already an entry for the given id , try changing the id");
        }
    }

    //Get Logic
    public List<JournalEntry> getEntry(){
        return journalEntryRepository.findAll();
    }

    //Get Logic by Id
    public Optional<JournalEntry> getEntryById(Long id){
        return journalEntryRepository.findById(id);
    }

    //Delete Logic
    public String deleteEntryById(Long id){
        journalEntryRepository.deleteById(id);
        return "deleted successfully";
    }

    //Post Logic is in JournalEntryController Class

}
