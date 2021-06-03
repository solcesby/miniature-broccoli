package app.utils.processor.impl;

import app.entity.user.User;
import app.service.UserService;
import app.service.impl.UserServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;

import java.util.Scanner;

import static app.entity.user.enums.Role.ADMIN;
import static app.entity.user.enums.Role.USER;

public class UserCreateProcessor implements Processor {
    private final UserService userService = new UserServiceImpl();

    @Override
    public boolean supports(String command) {
        return "user_create".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isAdmin()) {
            final Scanner sc = new Scanner(System.in);

            System.out.print("Name: ");
            final String name = sc.nextLine();
            System.out.println();

            System.out.print("Last name: ");
            final String lastName = sc.nextLine();
            System.out.println();

            System.out.print("Email: ");
            final String email = sc.nextLine();
            System.out.println();

            System.out.print("Password: ");
            final String password = sc.nextLine();
            System.out.println();

            userService.save(User.builder()
                    .name(name)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .role(USER)
                    .build());
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getUser().getRole());
    }
}
