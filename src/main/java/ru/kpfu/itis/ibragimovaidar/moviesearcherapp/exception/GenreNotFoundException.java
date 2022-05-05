package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception;

public class GenreNotFoundException extends NotFoundException {

    public GenreNotFoundException(){
        super("Genre not found");
    }

    public GenreNotFoundException(String message) {
        super(message);
    }
}
