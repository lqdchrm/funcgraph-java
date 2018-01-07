package funcgraphdemo.funcgraph.blocks.math;

import funcgraphdemo.funcgraph.blocks.domain.Stichprobe;
import funcgraphdemo.funcgraph.runtime.Block;
import funcgraphdemo.funcgraph.runtime.Scope;
import java.util.List;


public class Anzahl extends Block<Long>{

    public Anzahl(Scope scope) {
        super(scope, Stichprobe.class);
    }
    
    @Override
    protected Long calculate(Scope scope) {
        List<Double> stichprobe = scope.get(Stichprobe.class).getOutput();
        return stichprobe.stream().count();
    }
}
