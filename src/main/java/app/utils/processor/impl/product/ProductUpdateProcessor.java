package app.utils.processor.impl.product;

import app.entity.product.Product;
import app.service.impl.ProductService;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

import static app.entity.user.enums.Role.ADMIN;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;
import static app.utils.validators.ProductValidator.validate;

@Log4j2
public class ProductUpdateProcessor implements Processor {
    private final ProductService productService = new ProductService();

    @Override
    public boolean supports(String command) {
        return "product_update".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isCurrentUserSignedIn() && isAdmin()) {
            final Long id = Long.parseLong(command.split(" ")[1]);
            final Product productToUpdate = productService.getById(id);
            final Scanner sc = new Scanner(System.in);

            System.out.print("Name: ");
            final String name = sc.nextLine();
            System.out.println();

            System.out.print("Price: ");
            final Double price = Double.parseDouble(sc.nextLine());
            System.out.println();

            System.out.print("Description: ");
            final String description = sc.nextLine();
            System.out.println();

            productToUpdate.setName(name);
            productToUpdate.setPrice(price);
            productToUpdate.setDescription(description);

            validate(productToUpdate);

            productService.update(productToUpdate);
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getCurrentUser().getRole());
    }
}
