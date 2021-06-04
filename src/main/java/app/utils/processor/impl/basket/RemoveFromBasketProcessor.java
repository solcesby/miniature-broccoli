package app.utils.processor.impl.basket;

import app.entity.product.Product;
import app.entity.user.User;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;

import java.util.List;

public class RemoveFromBasketProcessor implements Processor {
    @Override
    public boolean supports(String command) {
        return "remove".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        final User user = SecurityContextHolder.getCurrentUser();
        final Long id = Long.parseLong(command.split(" ")[1]);
        final List<Product> userBasket = user.getBasket();

        userBasket.removeIf(p -> id.equals(p.getId()));
        user.setBasket(userBasket);
    }
}
