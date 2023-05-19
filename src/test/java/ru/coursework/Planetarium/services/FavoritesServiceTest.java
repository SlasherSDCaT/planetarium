package ru.coursework.Planetarium.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.coursework.Planetarium.entity.Article;
import ru.coursework.Planetarium.entity.Person;
import ru.coursework.Planetarium.repositories.PersonRepository;
import ru.coursework.Planetarium.security.AuthenticatedPersonService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FavoritesServiceTest {
    @Mock
    private PersonRepository personRepository;

    @Mock
    private AuthenticatedPersonService authenticatedPersonService;

    private FavoritesService favoritesService;

    @BeforeEach
    void setUp() {
        this.favoritesService = new FavoritesService(personRepository, authenticatedPersonService);
    }

    @Test
    void addArticleToFavorites() {
        // Arrange
        Article article = new Article();
        Person personToAddFav = new Person();
        Mockito.when(authenticatedPersonService.getAuthenticatedPerson()).thenReturn(personToAddFav);

        // Act
        favoritesService.addArticleToFavorites(article);

        // Assert
        assertTrue(personToAddFav.getFavorites().contains(article));
        assertTrue(article.getFollowers().contains(personToAddFav));
        verify(personRepository).save(personToAddFav);
    }

    @Test
    void delArticleFromFavorites() {
        // Arrange
        Article article = new Article();
        Person personToDelFromFav = new Person();
        personToDelFromFav.getFavorites().add(article);

        // Act
        favoritesService.delArticleFromFavorites(article, personToDelFromFav);

        // Assert
        assertFalse(personToDelFromFav.getFavorites().contains(article));
        assertFalse(article.getFollowers().contains(personToDelFromFav));
        verify(personRepository).save(personToDelFromFav);
    }
}

