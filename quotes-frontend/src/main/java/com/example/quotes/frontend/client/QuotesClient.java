package com.example.quotes.frontend.client;

import com.example.quotes.frontend.client.model.Quote;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client("quotes")
public interface QuotesClient {
    @Get
    List<Quote> getQuotes();

    @Get("/quotes/{id}")
    Quote getQuote(String id);

    @Get("/quotes/?character={character}")
    List<Quote> getQuoteByCharacter(String character);
}
