package com.newproject.journalapp.repository;

import com.newproject.journalapp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
// Making connection to MongoDB
public interface JournalEntryRepository extends MongoRepository<JournalEntry,Long> {

}
