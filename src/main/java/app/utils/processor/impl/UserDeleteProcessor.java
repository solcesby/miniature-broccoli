package app.utils.processor.impl;

import app.service.UserService;
import app.service.impl.UserServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;

import static app.entity.user.enums.Role.ADMIN;

public class UserDeleteProcessor implements Processor {

    private final UserService userService = new UserServiceImpl();

    @Override
    public boolean supports(String command) {
        return "user_delete".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        if (isAdmin()) {
            final Long id = Long.parseLong(command.split(" ")[1]);

            userService.deleteById(id);
        }
    }

    private boolean isAdmin() {
        return ADMIN.equals(SecurityContextHolder.getUser().getRole());
    }
}
