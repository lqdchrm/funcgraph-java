package funcgraphdemo.funcgraph.blocks.math;

import funcgraphdemo.funcgraph.blocks.domain.Stichprobe;
import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;
import java.util.List;

public class Varianz extends Block<Double> {

    public Varianz(Scope scope) {
        super(scope, Stichprobe.class, Mittelwert.class);
    }

    @Override
    protected Double calculate(Scope scope) {
        List<Double> stichprobe = scope.get(Stichprobe.class).getOutput();
        Double mittelwert = scope.get(Mittelwert.class).getOutput();
        
        int n = stichprobe.size();
        double result = stichprobe.stream()
                .map(v -> Math.pow((mittelwert - v), 2)) // quadratische abweichungen
                .reduce(0.0, (a,b) -> a + b)                         // summe
                / n;                                                 // durch anzahl
        return result;
    }   
    
}
