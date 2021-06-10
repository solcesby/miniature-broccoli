package app.api;

import app.utils.processor.Processor;
import app.utils.processor.impl.basket.AddToBasketProcessor;
import app.utils.processor.impl.basket.RemoveFromBasketProcessor;
import app.utils.processor.impl.page.*;
import app.utils.processor.impl.product.ProductCreateProcessor;
import app.utils.processor.impl.product.ProductDeleteProcessor;
import app.utils.processor.impl.product.ProductUpdateProcessor;
import app.utils.processor.impl.user.UserCreateProcessor;
import app.utils.processor.impl.user.UserDeleteProcessor;
import app.utils.processor.impl.user.UserUpdateProcessor;

import java.util.List;

import static app.api.enums.Page.*;
import static app.utils.LineReader.readLine;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;
import static app.utils.UserStateValidator.isAdmin;

public class Menu {

    private final List<Processor> processors = List.of(
            new AddToBasketProcessor(),
            new RemoveFromBasketProcessor(),

            new ProductCreateProcessor(),
            new ProductUpdateProcessor(),
            new ProductDeleteProcessor(),
            new UserCreateProcessor(),
            new UserUpdateProcessor(),
            new UserDeleteProcessor(),

            new BasketPageProcessor(),
            new ExitPageProcessor(),
            new LoginPageProcessor(),
            new ProfilePageProcessor(),
            new StorePageProcessor(),
            new UsersPageProcessor()
    );

    public void show() {
        try {
            while (true) {
                if (isCurrentUserSignedIn() && isAdmin()) {
                    System.out.println(PANEL_ADMIN);
                } else if (isCurrentUserSignedIn()) {
                    System.out.println(PANEL_SIGNED_IN);
                } else {
                    System.out.println(PANEL);
                }
                final String input = readLine();
                processors.stream().filter(processor -> processor.supports(input))
                        .findAny().ifPresentOrElse(
                        processor -> processor.process(input),
                        () -> System.out.printf("Unknown command %s ", input));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            show();
        }
    }
}
