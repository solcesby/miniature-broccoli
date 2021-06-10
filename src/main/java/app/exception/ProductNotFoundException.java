package app.exception;

import static java.lang.String.format;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(Long id) {
        super(format("Product with id %d not found", id));
    }

}
