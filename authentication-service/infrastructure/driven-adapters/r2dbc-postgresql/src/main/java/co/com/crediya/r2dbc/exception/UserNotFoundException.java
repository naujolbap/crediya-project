package co.com.crediya.r2dbc.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found in the database");
    }
}
