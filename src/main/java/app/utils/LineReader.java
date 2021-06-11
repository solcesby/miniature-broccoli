package app.utils;

import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

@Log4j2
public class LineReader {

    public static String readLine() {
        Scanner sc = new Scanner(System.in);
        log.info("scanner created");
        return sc.nextLine();
    }
}
