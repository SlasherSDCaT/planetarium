package ru.coursework.Planetarium.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coursework.Planetarium.entity.Article;
import ru.coursework.Planetarium.entity.Comment;
import ru.coursework.Planetarium.entity.Person;
import ru.coursework.Planetarium.repositories.CommentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ArticleService articleService;
    private final CommentRepository commentRepository;

    public void create(Comment comment, Long articleId, Person person) {
        comment.setAuthor(person);
        Article article = articleService.getById(articleId);
        comment.setArticle(article);
        comment.setDateCreation(LocalDate.now());
        comment.setAuthorName(person.getName());
        commentRepository.save(comment);
    }

    public List<Comment> findAllByFeedId(Long id) {
        return commentRepository.findAllByArticleId(id);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

}
