package app.utils.processor.impl.page;

import app.service.ProductService;
import app.service.impl.ProductServiceImpl;
import app.utils.processor.Processor;

import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;

public class StorePageProcessor implements Processor {
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public boolean supports(String command) {
        return "1".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isCurrentUserSignedIn()) {
            productService.getAll().forEach(System.out::println);
        }
    }
}
