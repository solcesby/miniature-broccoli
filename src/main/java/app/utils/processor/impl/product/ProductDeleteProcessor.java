package app.utils.processor.impl.product;

import app.service.impl.ProductService;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import static app.entity.user.enums.Role.ADMIN;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;

@Log4j2
public class ProductDeleteProcessor implements Processor {
    private final ProductService productService = new ProductService();

    @Override
    public boolean supports(String command) {
        return "product_delete".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isCurrentUserSignedIn() && isAdmin()) {
            final Long id = Long.parseLong(command.split(" ")[1]);

            productService.deleteById(id);
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getCurrentUser().getRole());
    }
}
