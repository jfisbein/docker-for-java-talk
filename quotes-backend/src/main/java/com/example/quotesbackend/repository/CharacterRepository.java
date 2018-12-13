package com.example.quotesbackend.repository;

import com.example.quotesbackend.model.Quote;
import com.mongodb.client.DistinctIterable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class CharacterRepository {
    private final MongoTemplate mongoTemplate;

    public List<String> getCharacters() {
        DistinctIterable<String> iterable = mongoTemplate.getCollection(mongoTemplate.getCollectionName(Quote.class)).distinct("character", String.class);
        return StreamSupport.stream(iterable.spliterator(), false)
                .sorted()
                .collect(Collectors.toList());
    }
}
