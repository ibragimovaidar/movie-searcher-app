package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.commentary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.AbstractEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.UserEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class CommentaryAbstractEntity extends AbstractEntity {

    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity author;
}
