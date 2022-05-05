package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception;

public class PersonNotFoundException extends NotFoundException {

    public PersonNotFoundException(){
        super("Person not found");
    }

    public PersonNotFoundException(String message) {
        super(message);
    }
}
