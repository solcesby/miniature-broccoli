package app.utils.processor.impl.basket;

import app.entity.product.Product;
import app.entity.user.User;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static app.utils.SecurityContextHolder.getCurrentUser;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;

@Log4j2
public class RemoveFromBasketProcessor implements Processor {
    @Override
    public boolean supports(String command) {
        return "remove".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isCurrentUserSignedIn()) {
            final User user = getCurrentUser();
            final Long id = Long.parseLong(command.split(" ")[1]);
            final List<Product> userBasket = user.getBasket();

            userBasket.removeIf(p -> id.equals(p.getId()));
            user.setBasket(userBasket);
            log.info("successfully removed item with id {} from basket of {}", id, user);
        }
    }
}
