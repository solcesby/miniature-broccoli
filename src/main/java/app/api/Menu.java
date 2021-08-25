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
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static app.api.enums.Page.*;
import static app.utils.LineReader.readLine;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;
import static app.utils.validators.UserStateValidator.isAdmin;

@Log4j2
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
        log.info("started");
        try {
            while (true) {
                log.info("in while block");
                if (isCurrentUserSignedIn() && isAdmin()) {
                    System.out.println(PANEL_ADMIN);
                } else if (isCurrentUserSignedIn()) {
                    System.out.println(PANEL_SIGNED_IN);
                } else {
                    System.out.println(PANEL);
                }
                final String input = readLine();
                log.info("got '{}' command", input);
                processors.stream().filter(processor -> processor.supports(input))
                        .findAny().ifPresentOrElse(
                        processor -> {
                            processor.process(input);
                            log.info("processor {} supports command {}",
                                    processor.getClass().getSimpleName(), input);
                        },
                        () -> {
                            System.out.printf("Unknown command %s %n", input);
                            log.warn("unknown command '{}'", input);
                        });
            }
        } catch (Exception e) {
            log.warn("exception caught");
            e.printStackTrace();
        } finally {
            log.info("finally block");
            show();
        }
    }
}