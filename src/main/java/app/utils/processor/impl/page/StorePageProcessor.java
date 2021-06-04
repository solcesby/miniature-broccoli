package app.utils.processor.impl.page;

import app.service.ProductService;
import app.service.impl.ProductServiceImpl;
import app.utils.processor.Processor;

import static app.utils.UserStateValidator.isAdmin;
import static app.utils.UserStateValidator.isSignedIn;

public class StorePageProcessor implements Processor {
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public boolean supports(String command) {
        return "1".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isSignedIn() && isAdmin()) {
            productService.getAll().forEach(System.out::println);
        } else if (isSignedIn()) {
            productService.getAll().forEach(System.out::println);
        }
    }
}
