import org.json.JSONException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("""
                NOTE: Converts only from EUR
                You may choose from the following:
                > USD (US Dollars)
                > AUD (Australian Dollars)
                > CAD (Canadian Dollars)
                > PLN (Polish Zloty)
                > MXN (Mexican Peso)
                                
                Please make sure to only enter the currency code!
                """);

        Scanner scanner = new Scanner(System.in);

        //System.out.println("Convert from: ");
        //String from = scanner.nextLine();

        double conversionRate = 0;
        boolean validCode = false;
        String to = null;

        while (validCode == false) {
            try {
                System.out.println("Convert to: ");
                to = scanner.nextLine();
                conversionRate = CurrencyConverter.getRate(to);
                validCode = true;
            } catch (JSONException invalidCode) {
                System.out.println("Caught JSONException, currency code is not supported, try another");
            }
        }

        boolean validAmount = true;
        String amount = null;

        while (validAmount == true) {
            System.out.println("Enter amount: ");
            amount = scanner.nextLine();
            if (Double.parseDouble(amount) < 0) {
                System.out.println("Error: Invalid amount!");
            } else {
                validAmount = false;
            }
        }

        double convertedAmount = (Double.parseDouble(amount)) * conversionRate;
        System.out.printf("%s EUR  ---> %.2f %s", amount, convertedAmount, to);
    }
}

