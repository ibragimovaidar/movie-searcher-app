package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.PersonResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.PersonEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PersonMapper {

    PersonResponse personToPersonResponse(PersonEntity person);
}
