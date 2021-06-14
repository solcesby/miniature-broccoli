package app.utils.processor.impl.product;

import app.entity.product.Product;
import app.service.ProductService;
import app.service.impl.ProductServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;
import app.utils.validators.ProductValidator;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

import static app.entity.user.enums.Role.ADMIN;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;
import static app.utils.validators.ProductValidator.validate;

@Log4j2
public class ProductCreateProcessor implements Processor {
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public boolean supports(String command) {
        return "product_create".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isCurrentUserSignedIn() && isAdmin()) {
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

            final Product productToSave = Product.builder()
                    .name(name)
                    .price(price)
                    .description(description)
                    .build();

            validate(productToSave);

            productService.save(productToSave);
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getCurrentUser().getRole());
    }
}
