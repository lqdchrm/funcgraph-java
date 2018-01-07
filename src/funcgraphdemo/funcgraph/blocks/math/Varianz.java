package funcgraphdemo.funcgraph.blocks.math;

import funcgraphdemo.funcgraph.blocks.domain.Stichprobe;
import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;

public class Varianz extends Block<Double> {

    public Varianz(Scope scope) {
        super(scope, Stichprobe.class, Mittelwert.class);
    }

    @Override
    protected Double calculate(Block... inputs) {
        Stichprobe stichprobe = (Stichprobe)inputs[0];
        Mittelwert mittelwert = (Mittelwert)inputs[1];
        
        int n = stichprobe.getOutput().size();
        double result = stichprobe.getOutput().stream()
                .map(v -> Math.pow((mittelwert.getOutput() - v), 2)) // quadratische abweichungen
                .reduce(0.0, (a,b) -> a + b)                         // summe
                / n;                                                 // durch anzahl
        return result;
    }   
    
}
