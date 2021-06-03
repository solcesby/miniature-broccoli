package app.utils.processor.impl;

import app.entity.user.User;
import app.service.UserService;
import app.service.impl.UserServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;

import java.util.Scanner;

import static app.entity.user.enums.Role.ADMIN;

public class UserUpdateProcessor implements Processor {
    private final UserService userService = new UserServiceImpl();

    @Override
    public boolean supports(String command) {
        return "user_update".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isAdmin()) {
            final Long id = Long.parseLong(command.split(" ")[1]);
            final User userToUpdate = userService.getById(id);
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

            userToUpdate.setName(name);
            userToUpdate.setLastName(lastName);
            userToUpdate.setEmail(email);
            userToUpdate.setPassword(password);

            userService.update(userToUpdate);
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getUser().getRole());
    }
}
