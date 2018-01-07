package funcgraphdemo.domain;

import funcgraphdemo.common.Calculation;
import funcgraphdemo.common.ValueProvider;
import java.util.ArrayList;
import java.util.List;

public class Stichprobe implements Calculation<ValueProvider, List<Double>> {

    protected final ArrayList<Double> values;

    public Stichprobe() {
        this.values = new ArrayList<>();
        this.values.add(1.0);
        this.values.add(2.0);
        this.values.add(3.0);
    }

    @Override
    public List<Double> calculate(ValueProvider scope) {
        return this.values;
    }
}
