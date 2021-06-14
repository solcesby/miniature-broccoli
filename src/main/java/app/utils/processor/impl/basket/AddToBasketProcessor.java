package app.utils.processor.impl.basket;

import app.entity.product.Product;
import app.entity.user.User;
import app.service.impl.ProductService;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static app.utils.SecurityContextHolder.getCurrentUser;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;

@Log4j2
public class AddToBasketProcessor implements Processor {
    private final ProductService productService = new ProductService();

    @Override
    public boolean supports(String command) {
        return "add".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if ((isCurrentUserSignedIn())) {
            final User user = getCurrentUser();
            final Long id = Long.parseLong(command.split(" ")[1]);
            final Product product = productService.getById(id);
            List<Product> userBasket = user.getBasket();

            userBasket.add(product);
            user.setBasket(userBasket);
            log.info("{} successfully added to basket of {}", product, user);
        }
    }
}
