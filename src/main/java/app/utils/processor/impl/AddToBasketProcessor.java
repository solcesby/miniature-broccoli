package app.utils.processor.impl;

import app.entity.Product;
import app.entity.User;
import app.service.ProductService;
import app.service.impl.ProductServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;

import java.util.List;

public class AddToBasketProcessor implements Processor {
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public boolean supports(String command) {
        return "add".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        final User user = SecurityContextHolder.getUser();
        final Long id = Long.parseLong(command.split(" ")[1]);
        final Product product = productService.getById(id);
        List<Product> userBasket = user.getBasket();

        userBasket.add(product);
        user.setBasket(userBasket);
    }
}
