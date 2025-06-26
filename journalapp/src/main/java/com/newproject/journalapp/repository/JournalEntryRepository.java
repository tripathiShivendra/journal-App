package com.newproject.journalapp.repository;

import com.newproject.journalapp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry,Long> {

}
