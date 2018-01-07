package funcgraphdemo.funcgraph.blocks.math;

import funcgraphdemo.funcgraph.blocks.domain.Stichprobe;
import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;

public class Summe extends Block<Double>{

    public Summe(Scope scope) {
        super(scope, Stichprobe.class);
    }
    
    @Override
    protected Double calculate(Block... inputs) {
        Stichprobe stichprobe = (Stichprobe)inputs[0];
        return stichprobe.getOutput().stream().reduce(0.0, (a,b) -> a+b);
    }
}
