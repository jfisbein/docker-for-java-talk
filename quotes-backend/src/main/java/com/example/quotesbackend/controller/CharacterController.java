package com.example.quotesbackend.controller;

import com.example.quotesbackend.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterRepository repository;

    @GetMapping("")
    public List<String> getCharacters() {
        return repository.getCharacters();
    }
}
