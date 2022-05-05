package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.commentary.MovieCommentaryEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "movie")
public class MovieEntity extends ImageAbstractEntity {

    private String name;

    private String description;

    @Column(name = "release_date")
    private LocalDate dateOfRelease;

    private String country;

    @Column(name = "average_rating")
    private Integer averageRating;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private GenreEntity genre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_person",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id")
    )
    private Set<PersonEntity> persons;

    @OneToMany(mappedBy = "movie")
    private Set<MovieCommentaryEntity> commentaries;

    @OneToMany(mappedBy = "movie")
    private Set<ReviewEntity> reviews;
}
