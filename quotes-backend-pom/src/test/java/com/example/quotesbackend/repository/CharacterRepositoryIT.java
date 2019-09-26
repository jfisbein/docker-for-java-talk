package com.example.quotesbackend.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.quotesbackend.model.Quote;
import java.util.List;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {CharacterRepositoryIT.Initializer.class})
public class CharacterRepositoryIT {

  @Autowired
  private CharacterRepository repository;

  @Autowired
  private MongoTemplate mongoTemplate;

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues
          .of("spring.data.mongodb.uri=mongodb://localhost:27018/test")
          .applyTo(configurableApplicationContext.getEnvironment());
    }
  }

  @Test
  public void getCharactersEmptyCollection() {
    // Given
    dropQuotesCollection();

    // When
    List<String> characters = repository.getCharacters();

    // Then
    assertTrue(characters.isEmpty());
  }

  @Test
  public void getCharactersSingleCharacter() {
    // Given
    dropQuotesCollection();
    insertOneQuote("Homer Simpson", "¡Por favor, no me comáis! Tengo mujer e hijos ¡Comeros a ellos!");
    insertOneQuote("Homer Simpson", "Ahora con Internet los niños se crían solos.");

    // When
    List<String> characters = repository.getCharacters();

    // Then
    assertEquals(1, characters.size());
    assertEquals("Homer Simpson", characters.get(0));
  }

  @Test
  public void getCharactersMultipleCharacters() {
    // Given
    dropQuotesCollection();
    insertOneQuote("Homer Simpson", "¡Por favor, no me comáis! Tengo mujer e hijos ¡Comeros a ellos!");
    insertOneQuote("Homer Simpson", "Ahora con Internet los niños se crían solos.");
    insertOneQuote("Bart Simpson", "Multiplícate por cero.");
    insertOneQuote("Lisa Simpson", "Cállate, cerebro. Ahora tengo amigos, ya no te necesito.");
    insertOneQuote("Ralph", "¡Soy un unicornio retrasado!");

    // When
    List<String> characters = repository.getCharacters();

    // Then
    assertEquals(4, characters.size());

    // Alphabetically sorted
    assertEquals("Bart Simpson", characters.get(0));
    assertEquals("Homer Simpson", characters.get(1));
    assertEquals("Lisa Simpson", characters.get(2));
    assertEquals("Ralph", characters.get(3));
  }

  private void dropQuotesCollection() {
    String collectionName = mongoTemplate.getCollectionName(Quote.class);
    mongoTemplate.getCollection(collectionName).drop();
  }

  private void insertOneQuote(String character, String text) {
    String collectionName = mongoTemplate.getCollectionName(Quote.class);
    Document quote = new Document();
    quote.append("character", character);
    quote.append("text", text);
    quote.append("_class", Quote.class.getCanonicalName());

    mongoTemplate.getCollection(collectionName).insertOne(quote);
  }
}