package app.utils.validators;

import app.entity.product.Product;
import app.service.impl.ProductService;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Pattern;

import static app.utils.Constants.NAME_REGEX;
import static app.utils.Constants.PRICE_REGEX;

@Log4j2
public class ProductValidator {
    private static final ProductService productService = new ProductService();

    private ProductValidator() {
        log.warn("utility class constructor called");
        throw new IllegalStateException("Utility class");
    }

    public static void validate(final Product product) {
        log.info("validating {}", product);
        if (!Pattern.matches(NAME_REGEX, product.getName())) {
            System.out.println("Incorrect name!");
            throw new IllegalArgumentException("Incorrect name!");
        } else if (productService.getAll().stream().anyMatch(p -> p.getName().equalsIgnoreCase(product.getName()))) {
            System.out.println("Product already exists!");
            throw new IllegalArgumentException("Product already exists!");
        }
        if (!Pattern.matches(PRICE_REGEX, product.getPrice().toString())) {
            System.out.println("Incorrect price!");
            throw new IllegalArgumentException("Incorrect price!");
        }
        log.info("product {} successfully validated", product);
    }

}
