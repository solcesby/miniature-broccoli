package app.utils.processor.impl.page;

import app.utils.processor.Processor;

public class ExitPageProcessor implements Processor {
    @Override
    public boolean supports(String command) {
        return "0".equals(command.split(" ")[0]);
    }

    @Override
    public void process(String command) {
        System.out.println("Come back soon!");
        System.exit(0);
    }
}
