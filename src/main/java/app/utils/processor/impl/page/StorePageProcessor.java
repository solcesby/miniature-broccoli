package app.utils.processor.impl.page;

import app.service.ProductService;
import app.service.impl.ProductServiceImpl;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;

@Log4j2
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
        log.info("showed store page");
    }
}
