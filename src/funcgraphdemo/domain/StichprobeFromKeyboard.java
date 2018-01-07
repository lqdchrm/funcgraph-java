package funcgraphdemo.domain;

import funcgraphdemo.common.ValueProvider;
import java.util.List;
import java.util.Scanner;

public class StichprobeFromKeyboard extends Stichprobe {

    @Override
    public List<Double> calculate(ValueProvider scope) {
        values.clear();
        System.out.println(String.format("Bitte geben Sie Zahlen ein (getrennt durch Space oder Enter):\n"));
        Scanner keyboard = new Scanner(System.in);

        boolean inputValid = true;

        while (inputValid && keyboard.hasNextLine()) {
            String line = keyboard.nextLine();
            String[] tokens = line.split(" ");
            if (tokens.length == 0) {
                inputValid = false;
            } else {
                for (String token : tokens) {
                    try {
                        double value = Double.parseDouble(token);
                        values.add(value);                          // write to inherited protected field
                    } catch (NumberFormatException ex) {
                        inputValid = false;
                    }
                }
            }
        }

        return values;
    }

}
