import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class ConverterTest {

    @Test
    public void generateTest()
    {
        System.out.println("Simple tests!");
    }

    @Test
    public void testCNYtoJPY() {
        PrintStream outBackup = System.out;
        OutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        Main.convertCNYtoJPY(100); // Получаем вывод в консоль
        System.setOut(outBackup);

        String[] str = os.toString().replace(',', '.').split(" ");

        Assertions.assertEquals(Math.round(Double.parseDouble(str[0]) * Main.getCurrencyRate())
                , Math.round(Double.parseDouble(str[3])));

    }

    @Test
    public void testJPYtoCNY() {
        PrintStream outBackup = System.out;
        OutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        Main.convertJPYtoCNY(500); // Получаем вывод в консоль
        System.setOut(outBackup);

        String[] str = os.toString().replace(',', '.').split(" ");

        Assertions.assertEquals(500.0
                , Math.round(Double.parseDouble(str[3]) * Main.getCurrencyRate()));

    }
}
