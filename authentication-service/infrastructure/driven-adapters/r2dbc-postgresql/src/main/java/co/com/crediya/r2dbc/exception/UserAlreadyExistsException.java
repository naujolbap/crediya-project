package co.com.crediya.r2dbc.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("User already exists in the database");
    }
}