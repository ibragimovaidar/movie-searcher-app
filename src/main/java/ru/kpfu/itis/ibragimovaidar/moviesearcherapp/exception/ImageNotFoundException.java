package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception;

import org.springframework.http.HttpStatus;

public class ImageNotFoundException extends NotFoundException {

    public ImageNotFoundException(){
        super("Image not found");
    }

    public ImageNotFoundException(String message) {
        super(message);
    }
}
