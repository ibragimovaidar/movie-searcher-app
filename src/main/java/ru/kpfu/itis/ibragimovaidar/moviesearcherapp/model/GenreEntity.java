package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.commentary.GenreCommentaryEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "genre")
public class GenreEntity extends ImageAbstractEntity {

    private String name;

    private String description;

    @OneToMany(mappedBy = "genre")
    private Set<GenreCommentaryEntity> commentaries;

    @OneToMany(mappedBy = "genre")
    private List<MovieEntity> movies;
}
