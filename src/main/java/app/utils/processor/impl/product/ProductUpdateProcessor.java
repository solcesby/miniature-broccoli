package app.utils.processor.impl.product;

import app.entity.product.Product;
import app.service.ProductService;
import app.service.impl.ProductServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;
import java.util.regex.Pattern;

import static app.entity.user.enums.Role.ADMIN;
import static app.utils.Constants.NAME_REGEX;
import static app.utils.Constants.PRICE_REGEX;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;

@Log4j2
public class ProductUpdateProcessor implements Processor {
    private final ProductService productService = new ProductServiceImpl();

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

            if (!Pattern.matches(NAME_REGEX, name)) {
                System.out.println("Incorrect name!");
                return;
            } else if (productService.getAll().stream().anyMatch(p -> p.getName().equalsIgnoreCase(name))) {
                System.out.println("Product already exists!");
                return;
            }

            System.out.print("Price: ");
            final Double price = Double.parseDouble(sc.nextLine());
            System.out.println();

            if (!Pattern.matches(PRICE_REGEX, price.toString())) {
                System.out.println("Incorrect price!");
                return;
            }

            System.out.print("Description: ");
            final String description = sc.nextLine();
            System.out.println();

            productToUpdate.setName(name);
            productToUpdate.setPrice(price);
            productToUpdate.setDescription(description);

            productService.update(productToUpdate);
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getCurrentUser().getRole());
    }
}
