package com.example.quotesbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "quotes")
public class Quote {
    @Id
    private String id;

    private final String character;
    private final String text;
}
