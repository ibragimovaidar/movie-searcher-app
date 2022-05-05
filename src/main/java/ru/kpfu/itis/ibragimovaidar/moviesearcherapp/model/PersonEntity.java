package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.commentary.MovieCommentaryEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.commentary.PersonCommentaryEntity;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "person")
public class PersonEntity extends ImageAbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    private String description;

    @ManyToMany(mappedBy = "persons")
    private Set<MovieEntity> movies;

    @OneToMany(mappedBy = "person")
    private Set<PersonCommentaryEntity> commentaries;
}
