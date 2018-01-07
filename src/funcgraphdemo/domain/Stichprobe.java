package funcgraphdemo.domain;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Stichprobe extends ArrayList<Double> {
    public Stichprobe() {
        this.add(1.0);
        this.add(2.0);
        this.add(3.0);
    }
    
    @Override
    public String toString() {
        return this.stream()
                .map(n -> n.toString())
                .collect(Collectors.joining(",", "[", "]"));
    }
}
