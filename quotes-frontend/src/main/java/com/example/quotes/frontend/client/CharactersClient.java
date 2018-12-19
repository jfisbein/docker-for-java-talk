package com.example.quotes.frontend.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client("${backend-url}/characters")
public interface CharactersClient {
    @Get("/")
    public List<String> getCharacters();
}
