package com.example.quotesbackend.controller;

import com.example.quotesbackend.model.Quote;
import com.example.quotesbackend.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuotesController {

    private final QuoteRepository repository;

    @GetMapping(value = "")
    public List<Quote> getQuotes(@RequestParam(value = "character", required = false) String character) {
        if (StringUtils.isEmpty(character)) {
            return repository.findAll();
        } else {
            return repository.findByCharacterIgnoreCase(character);
        }
    }

    @GetMapping(value = "/{id}")
    public Quote getQuote(@PathVariable(name = "id") String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
