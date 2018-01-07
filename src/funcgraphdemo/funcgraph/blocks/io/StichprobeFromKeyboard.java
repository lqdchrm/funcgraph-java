package funcgraphdemo.funcgraph.blocks.io;

import funcgraphdemo.funcgraph.blocks.domain.Stichprobe;
import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;
import java.util.Scanner;

public class StichprobeFromKeyboard extends Stichprobe {

    
    public StichprobeFromKeyboard(Scope scope) {
        super(scope);
    }

    @Override
    protected funcgraphdemo.domain.Stichprobe calculate(Block... inputs) {
        System.out.println(String.format("Bitte geben Sie Zahlen ein (getrennt durch Space oder Enter):\n"));
        Scanner keyboard = new Scanner(System.in);
        
        boolean inputValid = true;
        
        while(inputValid && keyboard.hasNextLine()) {
            String line = keyboard.nextLine();
            String[] tokens = line.split(" ");
            if (tokens.length == 0) {
                inputValid = false;
            } else {            
                for(String token : tokens) {
                    try {
                        double value = Double.parseDouble(token);
                        values.add(value);                          // write to inherited protected field
                    } catch(NumberFormatException ex) {
                        inputValid = false;
                    }
                }
            }
        }
        
        return values;
    }
    
}
