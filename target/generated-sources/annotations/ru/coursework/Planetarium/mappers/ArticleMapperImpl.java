package ru.coursework.Planetarium.mappers;

import javax.annotation.processing.Generated;
import ru.coursework.Planetarium.dto.ArticleDTO;
import ru.coursework.Planetarium.entity.Article;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-20T01:08:44+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public ArticleDTO converArticleToArticleDTO(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setId( article.getId() );
        articleDTO.setTitle( article.getTitle() );
        articleDTO.setDescription( article.getDescription() );

        return articleDTO;
    }
}
