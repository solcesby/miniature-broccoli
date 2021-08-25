package app.utils.processor.impl.user;

import app.entity.user.User;
import app.service.impl.UserService;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

import static app.entity.user.enums.Role.ADMIN;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;
import static app.utils.validators.UserValidator.validate;

@Log4j2
public class UserUpdateProcessor implements Processor {
    private final UserService userService = new UserService();

    @Override
    public boolean supports(String command) {
        return "user_update".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isCurrentUserSignedIn() && isAdmin()) {
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

            validate(userToUpdate);

            userService.update(userToUpdate);
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getCurrentUser().getRole());
    }
}