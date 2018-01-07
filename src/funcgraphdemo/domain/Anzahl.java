package funcgraphdemo.domain;

import funcgraphdemo.common.Calculation;
import funcgraphdemo.common.DependsOn;
import funcgraphdemo.common.ValueProvider;
import java.util.List;

@DependsOn(Stichprobe.class)
public class Anzahl implements Calculation<ValueProvider, Long> {
    
    @Override
    public Long calculate(ValueProvider scope) {
        List<Double> stichprobe = scope.get(Stichprobe.class);
        return stichprobe.stream().count();
    }

}
