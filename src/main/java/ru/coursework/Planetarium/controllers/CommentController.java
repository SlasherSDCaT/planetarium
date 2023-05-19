package ru.coursework.Planetarium.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.coursework.Planetarium.dto.CommentListDTO;
import ru.coursework.Planetarium.entity.Comment;
import ru.coursework.Planetarium.entity.Person;
import ru.coursework.Planetarium.mappers.CommentMapper;
import ru.coursework.Planetarium.security.AuthenticatedPersonService;
import ru.coursework.Planetarium.services.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final AuthenticatedPersonService authenticatedPersonService;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;


    @PostMapping("/add/{article_id}")
    public void create(@RequestBody Comment comment, @PathVariable("article_id") Long articleId) {
        Person person = authenticatedPersonService.getAuthenticatedPerson();
        commentService.create(comment, articleId, person);
    }

    @GetMapping("/get_all/{article_id}")
    public List<CommentListDTO> getAllFeedComments(@PathVariable("article_id") Long articleId) {
        return commentService.findAllByFeedId(articleId)
                .stream().map(commentMapper::convertCommentToCommentListDTO)
                .collect(Collectors.toList());
    }


    @PostMapping("/del/{comment_id}")
    public void delete(@PathVariable("comment_id") Long commentId) {
        commentService.delete(commentId);
    }

}
