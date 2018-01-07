package funcgraphdemo.domain;

import funcgraphdemo.common.Calculation;
import funcgraphdemo.common.ValueProvider;

public class StdAbw implements Calculation<ValueProvider, Double> {

    @Override
    public Double calculate(ValueProvider scope) {
        Double varianz = scope.get(Varianz.class);
        return Math.sqrt(varianz);
    }
}
