package funcgraphdemo.domain;

import funcgraphdemo.common.Calculation;
import funcgraphdemo.common.DependsOn;
import funcgraphdemo.common.ValueProvider;
import java.util.List;

@DependsOn({Stichprobe.class, Mittelwert.class})
public class Varianz implements Calculation<ValueProvider, Double> {

    @Override
    public Double calculate(ValueProvider scope) {
        List<Double> stichprobe = scope.get(Stichprobe.class);
        Double mittelwert = scope.get(Mittelwert.class);

        int n = stichprobe.size();
        double result = stichprobe.stream()
                .map(v -> Math.pow((mittelwert - v), 2)) // quadratische abweichungen
                .reduce(0.0, (a, b) -> a + b) // summe
                / n;                                                 // durch anzahl
        return result;
    }
}
