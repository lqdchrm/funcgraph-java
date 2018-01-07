package funcgraphdemo.domain;

import funcgraphdemo.common.Calculation;
import funcgraphdemo.common.DependsOn;
import funcgraphdemo.common.ValueProvider;
import java.util.List;

@DependsOn(Stichprobe.class)
public class Summe implements Calculation<ValueProvider, Double> {

    @Override
    public Double calculate(ValueProvider scope) {
        List<Double> stichprobe = scope.get(Stichprobe.class);
        return stichprobe.stream().reduce(0.0, (a, b) -> a + b);
    }
}
