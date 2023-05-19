package ru.coursework.Planetarium.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coursework.Planetarium.entity.Article;
import ru.coursework.Planetarium.entity.Person;
import ru.coursework.Planetarium.repositories.ArticleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final PersonService personService;
    private final FavoritesService favoritesService;

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Article getById(long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public void deleteArticle(long id) {
        for (Person p : personService.findAll()) {
            favoritesService.delArticleFromFavorites(getById(id), p);
        }
        articleRepository.deleteById(id);
    }

}
