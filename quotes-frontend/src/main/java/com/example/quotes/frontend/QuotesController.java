package com.example.quotes.frontend;

import com.example.quotes.frontend.client.CharactersClient;
import com.example.quotes.frontend.client.QuotesClient;
import com.example.quotes.frontend.client.model.Quote;
import com.google.common.collect.ImmutableMap;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.ModelAndView;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;

@Controller(value = "/")
@RequiredArgsConstructor
public class QuotesController {

    @Inject
    private final CharactersClient charactersClient;

    @Inject
    private final QuotesClient quotesClient;

    @Get
    public ModelAndView index() {
        System.out.println(charactersClient.getCharacters());
        return new ModelAndView("index", ImmutableMap.of("characters", charactersClient.getCharacters()));
    }

    @Get("quote/{character}")
    public ModelAndView getQuote(String character) {
        List<Quote> quoteByCharacter = quotesClient.getQuoteByCharacter(character);
        Quote quote = quoteByCharacter.get(new Random().nextInt(quoteByCharacter.size()));

        return new ModelAndView("index", ImmutableMap.of(
                "characters", charactersClient.getCharacters(),
                "quote", quote
        ));
    }
}
