package products.api.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author miguel.reyes on 2019-02-17.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product not found")
public class NotFound extends RuntimeException {

}