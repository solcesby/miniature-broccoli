package app.utils.processor.impl;

import app.service.ProductService;
import app.service.impl.ProductServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;

import static app.entity.user.enums.Role.ADMIN;

public class ProductDeleteProcessor implements Processor {
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public boolean supports(String command) {
        return "product_delete".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isAdmin()) {
            final Long id = Long.parseLong(command.split(" ")[1]);

            productService.deleteById(id);
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getUser().getRole());
    }
}
