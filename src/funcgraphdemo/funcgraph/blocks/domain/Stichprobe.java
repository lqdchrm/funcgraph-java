package funcgraphdemo.funcgraph.blocks.domain;

import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;

public class Stichprobe extends Block<funcgraphdemo.domain.Stichprobe>{

    protected final funcgraphdemo.domain.Stichprobe values;
    
    public Stichprobe(Scope scope, funcgraphdemo.domain.Stichprobe stichprobe) {
        super(scope);
        if (stichprobe != null) {
            this.values = stichprobe;
        } else {
            this.values = new funcgraphdemo.domain.Stichprobe();
        }
    }
    
    public Stichprobe(Scope scope) {
        this(scope, null);
    }
    
    @Override
    protected funcgraphdemo.domain.Stichprobe calculate(Scope scope) {
        return this.values;
    }    
}
