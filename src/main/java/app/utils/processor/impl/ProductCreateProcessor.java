package app.utils.processor.impl;

import app.entity.Product;
import app.service.ProductService;
import app.service.impl.ProductServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;

import java.util.Scanner;

import static app.entity.enums.Role.ADMIN;

public class ProductCreateProcessor implements Processor {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    public boolean supports(String command) {
        return "product_create".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isAdmin()) {
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

            productService.save(Product.builder()
                    .name(name)
                    .price(price)
                    .description(description)
                    .build());
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getUser().getRole());
    }
}
