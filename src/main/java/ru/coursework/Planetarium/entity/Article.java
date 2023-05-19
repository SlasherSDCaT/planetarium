package ru.coursework.Planetarium.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String title;

    String url_icon_img;

    String description;
    String body;

    String url_body_img;

    LocalDate creation_date;

    @JsonIgnore
    @Fetch(FetchMode.JOIN)
    @ManyToMany(mappedBy = "favorites")
    @ToString.Exclude
    private Set<Person> followers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "article")
    private List<Comment> feedComments;

}
