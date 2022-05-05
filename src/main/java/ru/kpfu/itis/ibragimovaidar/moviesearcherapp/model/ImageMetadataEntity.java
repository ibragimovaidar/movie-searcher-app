package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "image_metadata")
public class ImageMetadataEntity extends AbstractEntity {

    private String name;

    private String url;

    private Integer width;

    private Integer height;
}
