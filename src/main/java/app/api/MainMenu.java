package app.api;

import app.api.enums.Page;

import java.util.Scanner;

public class MainMenu {

    public void showPage(Page page) {
        System.out.println(page.getPanel());
        int pageNum = readPageNumber(page.getPanelSize());
        showPage(page.getPages().get(pageNum - 1));
    }

    private int readPageNumber(int panelSize) {
        final Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a number of page or 0 to exit: ");

            if (!sc.hasNextInt()) {
                System.out.printf("%s is not valid! Try again.%n", sc.next());
            } else {
                final int input = sc.nextInt();

                int validatedInput = validateInput(panelSize, input);
                if (validatedInput != -1) {
                    return validatedInput;
                }
            }
        }
    }

    private int validateInput(int panelSize, int input) {
        if (input == 0) {
            System.out.println("Come back soon!");
            System.exit(1);
        } else if (input >= 1 && input <= panelSize) {
            return input;
        } else {
            System.out.printf("There is no such page %d. " +
                            "Try again: enter a number between 1 and %d or 0 to exit.%n",
                    input, panelSize);
        }
        return -1;
    }
}
