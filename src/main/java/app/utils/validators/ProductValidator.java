package app.utils.validators;

import app.entity.product.Product;
import app.service.ProductService;
import app.service.impl.ProductServiceImpl;

import java.util.regex.Pattern;

import static app.utils.Constants.NAME_REGEX;
import static app.utils.Constants.PRICE_REGEX;

public class ProductValidator {
    private static final ProductService productService = new ProductServiceImpl();

    public static void validate(final Product product) {
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
    }

}
