package ru.coursework.Planetarium.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.coursework.Planetarium.dto.ArticleDTO;
import ru.coursework.Planetarium.mappers.ArticleMapper;
import ru.coursework.Planetarium.security.AuthenticatedPersonService;
import ru.coursework.Planetarium.services.ArticleService;
import ru.coursework.Planetarium.services.FavoritesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoritesService favoritesService;
    private final ArticleService articleService;
    private final AuthenticatedPersonService authenticatedPersonService;
    private final ArticleMapper articleMapper = ArticleMapper.INSTANCE;

    @GetMapping("/favorites")
    public List<ArticleDTO> getFavorites() {
        return authenticatedPersonService.getAuthenticatedPerson().getFavorites()
                .stream().map(articleMapper::converArticleToArticleDTO).collect(Collectors.toList());
    }

    @PostMapping("/add_to_fav/{id}")
    public void addArticleToFav(@PathVariable("id") int id) {
        favoritesService.addArticleToFavorites(articleService.getById(id));
    }

    @PostMapping("/del_from_fav/{id}")
    public void delArticleFromFav(@PathVariable("id") int id) {
        favoritesService.delArticleFromFavorites(articleService.getById(id),
                authenticatedPersonService.getAuthenticatedPerson());
    }
}
