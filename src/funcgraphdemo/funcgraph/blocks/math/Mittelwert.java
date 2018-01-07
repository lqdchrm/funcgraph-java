package funcgraphdemo.funcgraph.blocks.math;

import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;

public class Mittelwert extends Block<Double> {

    public Mittelwert(Scope scope) {
        super(scope, Summe.class, Anzahl.class);
    }
    
    @Override
    protected Double calculate(Scope scope) {
        Double summe = scope.get(Summe.class);
        Long anzahl = scope.get(Anzahl.class);
        
        return summe / anzahl;
    }
}
