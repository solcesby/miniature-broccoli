package app.utils.processor.impl.page;

import app.utils.processor.Processor;

import static app.utils.SecurityContextHolder.getCurrentUser;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;

public class ProfilePageProcessor implements Processor {
    @Override
    public boolean supports(String command) {
        return "3".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        final var user = getCurrentUser();
        if (isCurrentUserSignedIn()) {
            System.out.printf("Id: %s%n" +
                    "Name: %s%n" +
                    "Last Name: %s%n" +
                    "Email: %s%n" +
                    "Role: %s%n", user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getRole());
        }
    }
}
