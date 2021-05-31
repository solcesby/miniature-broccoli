package app.utils.processor.impl;

import app.entity.Product;
import app.service.ProductService;
import app.service.impl.ProductServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;

import java.util.Scanner;

import static app.entity.enums.Role.ADMIN;

public class ProductUpdateProcessor implements Processor {
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public boolean supports(String command) {
        return "product_update".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isAdmin()) {
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

            productService.update(productToUpdate);
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getUser().getRole());
    }
}
