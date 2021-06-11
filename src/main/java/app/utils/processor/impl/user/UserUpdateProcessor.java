package app.utils.processor.impl.user;

import app.entity.user.User;
import app.service.UserService;
import app.service.impl.UserServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;
import java.util.regex.Pattern;

import static app.entity.user.enums.Role.ADMIN;
import static app.utils.Constants.*;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;

@Log4j2
public class UserUpdateProcessor implements Processor {
    private final UserService userService = new UserServiceImpl();

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

            if (!Pattern.matches(NAME_REGEX, name)) {
                System.out.println("Incorrect name!");
                return;
            }

            System.out.print("Last name: ");
            final String lastName = sc.nextLine();
            System.out.println();

            if (!Pattern.matches(NAME_REGEX, lastName)) {
                System.out.println("Incorrect last name!");
                return;
            }

            System.out.print("Email: ");
            final String email = sc.nextLine();
            System.out.println();

            if (!Pattern.matches(EMAIL_REGEX, email)) {
                System.out.println("Incorrect email!");
                return;
            }

            System.out.print("Password: ");
            final String password = sc.nextLine();
            System.out.println();

            if (!Pattern.matches(PASSWORD_REGEX, password)) {
                System.out.println("Incorrect password!");
                return;
            }

            userToUpdate.setName(name);
            userToUpdate.setLastName(lastName);
            userToUpdate.setEmail(email);
            userToUpdate.setPassword(password);

            userService.update(userToUpdate);
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getCurrentUser().getRole());
    }
}
