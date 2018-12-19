package com.example.quotes.frontend.client;

import com.example.quotes.frontend.client.model.Quote;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client("${backend-url}/quotes")
public interface QuotesClient {
    @Get
    List<Quote> getQuotes();

    @Get("/{id}")
    Quote getQuote(String id);

    @Get("?character={character}")
    List<Quote> getQuoteByCharacter(String character);
}
