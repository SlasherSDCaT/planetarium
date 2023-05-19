package ru.coursework.Planetarium.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.coursework.Planetarium.dto.CommentListDTO;
import ru.coursework.Planetarium.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentListDTO convertCommentToCommentListDTO(Comment comment);
}
