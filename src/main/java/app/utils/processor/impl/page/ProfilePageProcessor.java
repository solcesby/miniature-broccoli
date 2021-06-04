package app.utils.processor.impl.page;

import app.entity.user.User;
import app.utils.processor.Processor;

import static app.utils.SecurityContextHolder.getCurrentUser;
import static app.utils.UserStateValidator.isAdmin;
import static app.utils.UserStateValidator.isSignedIn;

public class ProfilePageProcessor implements Processor {
    @Override
    public boolean supports(String command) {
        return "3".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        final User user = getCurrentUser();
        if (isSignedIn() && isAdmin()) {
            System.out.printf("Id: %s%n" +
                    "Name: %s%n" +
                    "Last Name: %s%n" +
                    "Email: %s%n" +
                    "Role: %s%n", user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getRole());
        } else if (isSignedIn()) {
            System.out.printf("Id: %s%n" +
                    "Name: %s%n" +
                    "Last Name: %s%n" +
                    "Email: %s%n" +
                    "Role: %s%n", user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getRole());
        }
    }
}
