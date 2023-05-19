package ru.coursework.Planetarium.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.coursework.Planetarium.entity.Article;
import ru.coursework.Planetarium.entity.Comment;
import ru.coursework.Planetarium.entity.Person;
import ru.coursework.Planetarium.repositories.CommentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private ArticleService articleService;

    @Mock
    private CommentRepository commentRepository;

    private CommentService commentService;

    @BeforeEach
    void setUp() {
        this.commentService = new CommentService(articleService, commentRepository);
    }

    @Test
    void createComment() {
        // Arrange
        Comment comment = new Comment();
        comment.setComment("Test comment");
        Long articleId = 1L;
        Person person = new Person();
        person.setName("Name");
        Article article = new Article();
        Mockito.when(articleService.getById(articleId)).thenReturn(article);

        // Act
        commentService.create(comment, articleId, person);

        // Assert
        assertNotNull(comment.getAuthor());
        assertEquals(person, comment.getAuthor());
        assertNotNull(comment.getArticle());
        assertEquals(article, comment.getArticle());
        assertNotNull(comment.getDateCreation());
        assertNotNull(comment.getAuthorName());
        verify(commentRepository).save(comment);
    }

    @Test
    void findAllCommentsByArticleId() {
        // Arrange
        Long articleId = 1L;
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1L, "Comment 1", LocalDate.now(), "Author 1", new Person(), new Article()));
        comments.add(new Comment(2L, "Comment 2", LocalDate.now(), "Author 2", new Person(), new Article()));
        Mockito.when(commentRepository.findAllByArticleId(articleId)).thenReturn(comments);

        // Act
        List<Comment> result = commentService.findAllByFeedId(articleId);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Comment 1", result.get(0).getComment());
        assertEquals("Comment 2", result.get(1).getComment());
        verify(commentRepository).findAllByArticleId(articleId);
    }

    @Test
    void deleteComment() {
        // Arrange
        Long commentId = 1L;

        // Act
        commentService.delete(commentId);

        // Assert
        verify(commentRepository).deleteById(commentId);
    }
}

