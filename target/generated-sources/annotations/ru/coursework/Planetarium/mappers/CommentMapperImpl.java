package ru.coursework.Planetarium.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.coursework.Planetarium.dto.CommentListDTO;
import ru.coursework.Planetarium.entity.Comment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-19T19:00:20+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentListDTO convertCommentToCommentListDTO(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentListDTO commentListDTO = new CommentListDTO();

        commentListDTO.setId( comment.getId() );
        commentListDTO.setComment( comment.getComment() );
        commentListDTO.setDateCreation( comment.getDateCreation() );
        commentListDTO.setAuthorName( comment.getAuthorName() );

        return commentListDTO;
    }
}
