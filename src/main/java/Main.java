import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        String menu = """
                \t1: Convert CNY to JPY
                \t2: Convert JPY to CNY
                \t3: Exit
                """;
        String err = "Invalid input. Please, enter the action number:";

        System.out.println("\t\tConverter.\n Please, enter the action number:\n" + menu);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                switch (reader.readLine()) {
                    case "1":
                        System.out.println("Enter the JPY value");
                        try {
                            convertCNYtoJPY(Double.parseDouble(reader.readLine()));
                        } catch (NumberFormatException e) {
                            System.out.println(err);
                        }
                        break;
                    case "2":
                        System.out.println("Enter the CNY value");
                        try {
                            convertJPYtoCNY(Double.parseDouble(reader.readLine()));
                        } catch (NumberFormatException e) {
                            System.out.println(err);
                        }
                        break;
                    case "3":
                        exit = true;
                    default:
                        System.out.println(err);
                }
                System.out.println(menu);
            } while (!exit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void convertCNYtoJPY(double value) {
        System.out.printf("%.2f CNY = %.2f JPY\n\n", value, value * getCurrencyRate());
    }

    public static void convertJPYtoCNY(double value) {
        System.out.printf("%.2f JPY = %.2f CNY\n\n", value, value / getCurrencyRate());
    }

    private static double getCurrencyRate() {
        Double rate = null;
        try {
            JsonNode json = new ObjectMapper().readTree(new URL("https://api.exchangerate.host/latest?base=CNY"));
            rate = json.get("rates").get("JPY").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rate;
    }
}
