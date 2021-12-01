package bowling.utils;

import java.util.Scanner;

public class InputUtils {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUtils() {
        throw new IllegalStateException("InputUtils 직접 생성 금지");
    }

    public static String input(String message) {
        System.out.print(message);
        return SCANNER.nextLine();
    }

    public static Integer inputToInteger(String message) {
        System.out.print(message);
        return Integer.parseInt(SCANNER.nextLine());
    }
}