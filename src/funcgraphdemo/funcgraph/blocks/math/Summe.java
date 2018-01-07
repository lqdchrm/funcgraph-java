package funcgraphdemo.funcgraph.blocks.math;

import funcgraphdemo.funcgraph.blocks.domain.Stichprobe;
import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;
import java.util.List;

public class Summe extends Block<Double>{

    public Summe(Scope scope) {
        super(scope, Stichprobe.class);
    }
    
    @Override
    protected Double calculate(Scope scope) {
        List<Double> stichprobe = scope.get(Stichprobe.class);
        return stichprobe.stream().reduce(0.0, (a,b) -> a+b);
    }
}
