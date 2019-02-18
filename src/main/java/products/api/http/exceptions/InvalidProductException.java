package products.api.http.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author miguel.reyes on 2019-02-17.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidProductException extends RuntimeException {

    public InvalidProductException(String message) {
        super(message);
    }
}