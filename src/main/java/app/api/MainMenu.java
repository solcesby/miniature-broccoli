package app.api;

import app.entity.Product;
import app.entity.User;
import app.service.ProductService;
import app.service.UserService;
import app.service.impl.ProductServiceImpl;
import app.service.impl.UserServiceImpl;
import app.utils.SecurityContextHolder;
import app.utils.processor.Processor;
import app.utils.processor.impl.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static app.entity.enums.Role.ADMIN;
import static app.entity.enums.Role.USER;

public class MainMenu {

    private static final String PANEL = "store | basket | profile | login";
    private static final String PANEL_SIGNED_IN = "store | basket | profile | logout";
    private static final String PANEL_ADMIN = "store | basket | profile | logout | users";
    private static final String LOGIN = "sign in | sign up";

    private final UserService userService = new UserServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final List<Processor> processors = List.of(
            new AddToBasketProcessor(),
            new RemoveFromBasketProcessor(),
            new ProductCreateProcessor(),
            new ProductUpdateProcessor(),
            new ProductDeleteProcessor(),
            new UserCreateProcessor(),
            new UserUpdateProcessor(),
            new UserDeleteProcessor()
    );

    public void showMain(final User user) {
        SecurityContextHolder.setUser(user);
        String pageToVisit;

        if (user.isLoggedIn()) {
            pageToVisit = getPageByRole(user);
        } else {
            System.out.println(PANEL);
            pageToVisit = PANEL;
        }

        goToPage(pageToVisit, user);
    }

    private void goToPage(final String pageToVisit, final User user) {
        final int pageNum = readCommand(pageToVisit);

        switch (pageNum) {
            case (1):
                if (user.isLoggedIn()) {
                    showStore(user);
                } else {
                    System.out.println("You need to sign in to visit this page");
                    showMain(user);
                }
                break;
            case (2):
                if (user.isLoggedIn()) {
                    showBasket(user);
                } else {
                    System.out.println("You need to sign in to visit this page");
                    showMain(user);
                }
                break;
            case (3):
                if (user.isLoggedIn()) {
                    showProfile(user);
                } else {
                    System.out.println("You need to sign in to visit this page");
                    showMain(user);
                }
                break;
            case (4):
                if (user.isLoggedIn()) {
                    logout(user);
                } else {
                    showLogin();
                }
                break;
            case (5):
                showUsers(user);
                break;
            default:
                showMain(user);
                break;
        }
    }

    private int readCommand(String page) {
        final Scanner sc = new Scanner(System.in);
        final int panelSize = page.split("\\|").length;

        while (true) {
            System.out.println("Enter a number of page, command or 0 to exit: ");

            if (!sc.hasNextInt()) {
                final String command = sc.nextLine();

                processors.forEach(p -> {
                    if (p.supports(command)) {
                        p.process(command);
                    }
                });

                return -1;
            } else {
                final int input = sc.nextInt();

                final int validatedInput = validateInput(panelSize, input);

                if (validatedInput != -1) {
                    return validatedInput;
                }
            }
        }
    }

    private int validateInput(final int panelSize, final int input) {
        if (input == 0) {
            System.out.println("Come back soon!");
            System.exit(1);
        } else if (input >= 1 && input <= panelSize) {
            return input;
        } else {
            System.out.printf("There is no such page %d.%n" +
                    "Try again: enter a number between 1 and %d or 0 to exit.%n", input, panelSize);
        }
        return -1;
    }

    private void showProfile(final User user) {
        final String pageToVisit = getPageByRole(user);

        System.out.printf("" +
                        "Name: %s%n" +
                        "Last Name: %s%n" +
                        "Email: %s%n" +
                        "Role: %s%n",
                user.getName(), user.getLastName(), user.getEmail(), user.getRole());

        goToPage(pageToVisit, user);
    }

    private void showStore(final User user) {
        final String pageToVisit = getPageByRole(user);

        List<Product> productList = productService.getAll();
        productList.forEach(System.out::println);

        goToPage(pageToVisit, user);
    }

    private void showBasket(final User user) {
        final String pageToVisit = getPageByRole(user);

        if (user.getBasket() != null) {
            user.getBasket().forEach(p -> System.out.println(p.toStringAtBasket()));
        }

        goToPage(pageToVisit, user);
    }

    private void logout(final User user) {
        user.setLoggedIn(false);
        showMain(user);
    }

    private void showLogin() {
        System.out.println(LOGIN);
        final int pageNum = readCommand(LOGIN);
        switch (pageNum) {
            case (1):
                showSignIn();
                break;
            case (2):
                showSignUp();
                break;
        }
    }

    private void showSignIn() {
        final User preUser = readEmailAndPassword();
        final Optional<User> optionalUser = Optional.ofNullable(userService.getByEmail(preUser.getEmail()));

        if (optionalUser.isPresent()) {
            final User user = optionalUser.get();
            if (preUser.getPassword().equals(user.getPassword())) {
                user.setLoggedIn(true);

                showMain(user);
            } else {
                System.out.println("Wrong password!");
                showSignIn();
            }
        } else {
            System.out.printf("Email %s is not found%n", preUser.getEmail());
            showSignIn();
        }
        //optionalUser.ifPresentOrElse();
    }

    private void showSignUp() {
        final Scanner sc = new Scanner(System.in);
        String email;

        do {
            System.out.print("Email: ");
            email = sc.nextLine();
            System.out.println();
        } while (isEmailUsed(email));

        Processor processor = new UserCreateProcessor();
        processor.process("user_create");
        User preUser = readEmailAndPassword();

        final Optional<User> optionalUser = Optional.ofNullable(userService.getByEmail(preUser.getEmail()));

        if (optionalUser.isEmpty()) {
            final User newUser = readCredentials(preUser);
            newUser.setLoggedIn(true);

            userService.save(newUser);

            showMain(newUser);
        } else {
            System.out.println("Email already registered!");
            showSignUp();
        }
        //optionalUser.orElse()
    }

    private boolean isEmailUsed(final String email) {
        return Optional.ofNullable(userService.getByEmail(email)).isPresent();
    }

    private String getPageByRole(User user) {
        String pageToVisit;

        if (ADMIN.equals(user.getRole())) {
            System.out.println(PANEL_ADMIN);
            pageToVisit = PANEL_ADMIN;
        } else {
            System.out.println(PANEL_SIGNED_IN);
            pageToVisit = PANEL_SIGNED_IN;
        }
        return pageToVisit;
    }

    private void showUsers(User user) {
        System.out.println(PANEL_ADMIN);
        List<User> userList = userService.getAll();
        userList.forEach(System.out::println);

        goToPage(PANEL_ADMIN, user);
    }

    private User readEmailAndPassword() {
        final Scanner sc = new Scanner(System.in);

        System.out.print("Email: ");
        final String email = sc.nextLine();
        System.out.println();

        System.out.print("Password: ");
        final String password = sc.nextLine();
        System.out.println();

        return User.builder()
                .email(email)
                .password(password)
                .build();
    }

    private User readCredentials(final User user) {
        final Scanner sc = new Scanner(System.in);

        System.out.print("Name: ");
        final String name = sc.nextLine();
        System.out.println();

        System.out.print("Last name: ");
        final String lastName = sc.nextLine();
        System.out.println();

        return User.builder()
                .name(name)
                .lastName(lastName)
                .email(user.getEmail())
                .password(user.getPassword())
                .role(USER)
                .build();
    }
}
