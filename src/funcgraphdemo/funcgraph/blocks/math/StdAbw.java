package funcgraphdemo.funcgraph.blocks.math;

import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;

public class StdAbw extends Block<Double> {

    public StdAbw(Scope scope) {
        super(scope, Varianz.class);
    }

    @Override
    protected Double calculate(Scope scope) {
        Double varianz = scope.get(Varianz.class);
        return Math.sqrt(varianz);
    }
    
}
