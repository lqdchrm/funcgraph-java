package funcgraphdemo.funcgraph.blocks.math;

import funcgraphdemo.funcgraph.blocks.domain.Stichprobe;
import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;


public class Anzahl extends Block<Long>{

    public Anzahl(Scope scope) {
        super(scope, Stichprobe.class);
    }
    
    @Override
    protected Long calculate(Block... inputs) {
        Stichprobe stichprobe = (Stichprobe)inputs[0];
        return stichprobe.getOutput().stream().count();
    }
}
