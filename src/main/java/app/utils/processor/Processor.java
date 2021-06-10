package app.utils.processor;

public interface Processor {

    boolean supports(String command);

    void process(String command);

}
