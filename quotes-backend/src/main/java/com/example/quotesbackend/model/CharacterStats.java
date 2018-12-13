package com.example.quotesbackend.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CharacterStats {
    private final String character;
    private final long count;
}
