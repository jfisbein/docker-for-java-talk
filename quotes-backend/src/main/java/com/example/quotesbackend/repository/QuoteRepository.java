package com.example.quotesbackend.repository;

import com.example.quotesbackend.model.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuoteRepository extends MongoRepository<Quote, String> {
    public List<Quote> findByCharacterIgnoreCase(String character);
}
