package funcgraphdemo.funcgraph.blocks.math;

import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;

public class Mittelwert extends Block<Double> {

    public Mittelwert(Scope scope) {
        super(scope, Summe.class, Anzahl.class);
    }
    
    @Override
    protected Double calculate(Block... inputs) {
        Summe summe = (Summe)inputs[0];
        Anzahl anzahl = (Anzahl)inputs[1];
        
        return summe.getOutput() / anzahl.getOutput();
    }
}
