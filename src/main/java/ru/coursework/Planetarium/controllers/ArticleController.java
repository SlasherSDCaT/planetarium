package ru.coursework.Planetarium.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.coursework.Planetarium.entity.Article;
import ru.coursework.Planetarium.services.ArticleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/add")
    public void addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
    }

    @PostMapping("/del/{id}")
    public void delArticle(@PathVariable("id") int id) {
        articleService.deleteArticle(id);
    }

    @GetMapping("/all")
    public List<Article> getArticles() {
        return articleService.getAll();
    }


}
