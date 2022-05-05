package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception;

import org.springframework.http.HttpStatus;

public class ImageUploadException extends ServiceException {

    public ImageUploadException() {
        super("Image upload error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ImageUploadException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
