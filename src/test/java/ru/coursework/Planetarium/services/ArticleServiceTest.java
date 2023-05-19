package ru.coursework.Planetarium.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.coursework.Planetarium.entity.Article;
import ru.coursework.Planetarium.entity.Person;
import ru.coursework.Planetarium.repositories.ArticleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private PersonService personService;

    @Mock
    private FavoritesService favoritesService;

    private ArticleService articleService;

    @BeforeEach
    void setUp() {
        this.articleService = new ArticleService(articleRepository, personService, favoritesService);
    }

    @Test
    void getAllArticles() {
        // Arrange
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1L, "Title 1", "url1", "Description 1", "Body 1", "url_body1", LocalDate.now(), new HashSet<>(), new ArrayList<>()));
        articles.add(new Article(2L, "Title 2", "url2", "Description 2", "Body 2", "url_body2", LocalDate.now(), new HashSet<>(), new ArrayList<>()));
        Mockito.when(articleRepository.findAll()).thenReturn(articles);

        // Act
        List<Article> result = articleService.getAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Title 1", result.get(0).getTitle());
        assertEquals("Title 2", result.get(1).getTitle());
        verify(articleRepository).findAll();
    }

    @Test
    void getById_existingId_returnsArticle() {
        // Arrange
        Article article = new Article(1L, "Title 1", "url1", "Description 1", "Body 1", "url_body1", LocalDate.now(), new HashSet<>(), new ArrayList<>());
        Mockito.when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        // Act
        Article result = articleService.getById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Title 1", result.getTitle());
        verify(articleRepository).findById(1L);
    }

    @Test
    void getById_nonExistingId_returnsNull() {
        // Arrange
        Mockito.when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Article result = articleService.getById(1L);

        // Assert
        assertNull(result);
        verify(articleRepository).findById(1L);
    }

    @Test
    void addArticle() {
        // Arrange
        Article article = new Article(1L, "Title 1", "url1", "Description 1", "Body 1", "url_body1", LocalDate.now(), new HashSet<>(), new ArrayList<>());

        // Act
        articleService.addArticle(article);

        // Assert
        verify(articleRepository).save(article);
    }

    @Test
    void deleteArticle() {
        // Arrange
        Article article = new Article(1L, "Title 1", "url1", "Description 1", "Body 1", "url_body1", LocalDate.now(), new HashSet<>(), new ArrayList<>());
        Mockito.when(articleRepository.findById(1L)).thenReturn(Optional.of(article));
        List<Person> persons = new ArrayList<>();
        persons.add(new Person());
        Mockito.when(personService.findAll()).thenReturn(persons);

        // Act
        articleService.deleteArticle(1L);

        // Assert
        verify(favoritesService).delArticleFromFavorites(article, persons.get(0));
        verify(articleRepository).deleteById(1L);
    }
}
