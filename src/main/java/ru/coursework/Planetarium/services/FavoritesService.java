package ru.coursework.Planetarium.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coursework.Planetarium.entity.Article;
import ru.coursework.Planetarium.entity.Person;
import ru.coursework.Planetarium.repositories.PersonRepository;
import ru.coursework.Planetarium.security.AuthenticatedPersonService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritesService {

    private final PersonRepository personRepository;
    private final AuthenticatedPersonService authenticatedPersonService;

    public void addArticleToFavorites(Article article) {
        Person personToAddFav = authenticatedPersonService.getAuthenticatedPerson();
        personToAddFav.getFavorites().add(article);
        article.getFollowers().add(personToAddFav);
        personRepository.save(personToAddFav);
    }

    public void delArticleFromFavorites(Article article, Person personToDelFromFav) {
        List<Article> list = personToDelFromFav.getFavorites()
                .stream().filter(o -> o.getId() != article.getId()).collect(Collectors.toList());
        article.getFollowers().remove(personToDelFromFav);
        personToDelFromFav.setFavorites(list);
        personRepository.save(personToDelFromFav);
    }

}
