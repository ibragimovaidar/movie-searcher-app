package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "review")
public class ReviewEntity extends AbstractEntity {

    private Integer rating;

    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String review;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private MovieEntity movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity author;
}
