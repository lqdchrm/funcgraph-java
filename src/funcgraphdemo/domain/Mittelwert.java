package funcgraphdemo.domain;

import funcgraphdemo.common.Calculation;
import funcgraphdemo.common.ValueProvider;

public class Mittelwert implements Calculation<ValueProvider, Double> {

    @Override
    public Double calculate(ValueProvider scope) {
        Double summe = scope.get(Summe.class);
        Long anzahl = scope.get(Anzahl.class);

        return summe / anzahl;
    }

}
