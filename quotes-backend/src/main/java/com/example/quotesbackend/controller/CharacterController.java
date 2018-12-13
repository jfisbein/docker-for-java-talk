package com.example.quotesbackend.controller;

import com.example.quotesbackend.model.CharacterStats;
import com.example.quotesbackend.model.Quote;
import com.example.quotesbackend.repository.CharacterRepository;
import com.example.quotesbackend.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterRepository repository;
    private final QuoteRepository quoteRepository;

    @GetMapping("")
    public List<String> getCharacters() {
        return repository.getCharacters();
    }

    @GetMapping("stats")
    public List<CharacterStats> getCharacterStats() {
        List<CharacterStats> stats = quoteRepository.findAll().stream()
                .collect(Collectors.groupingBy(Quote::getCharacter, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new CharacterStats(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(CharacterStats::getCount))
                .collect(Collectors.toList());
        Collections.reverse(stats);

        return stats;
    }
}
