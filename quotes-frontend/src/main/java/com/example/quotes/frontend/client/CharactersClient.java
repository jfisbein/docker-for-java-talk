package com.example.quotes.frontend.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client("backendcharacters")
public interface CharactersClient {
    @Get("characters")
    public List<String> getCharacters();
}
