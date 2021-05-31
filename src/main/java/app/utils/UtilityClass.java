package app.utils;

import app.entity.Product;
import app.entity.User;
import app.service.ProductService;
import app.service.UserService;
import app.service.impl.ProductServiceImpl;
import app.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class UtilityClass {

    private final UserService userService = new UserServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    private void addToBasket(User user, Long id) {
        List<Product> userBasket = user.getBasket();

        if (userBasket == null) {
            userBasket = new ArrayList<>();
        }

        userBasket.add(productService.getById(id));
        user.setBasket(userBasket);
    }

    private void removeFromBasket(User user, Long id) {
        List<Product> userBasket = user.getBasket();

        if (userBasket == null) {
            throw new IllegalArgumentException();
        }

        userBasket.removeIf(p -> id.equals(p.getId()));
        user.setBasket(userBasket);
    }
}
