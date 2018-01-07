package funcgraphdemo.common;

import java.util.Collection;

public interface ValueProvider {

    public <T> T get(Class<? extends Calculation<? extends ValueProvider, T>> type);

    public <T> void set(Class<? extends Calculation<? extends ValueProvider, T>> type, T value);
    
    public Collection<Class<? extends Calculation<? extends ValueProvider, ?>>> dependsOn(Class<? extends Calculation<? extends ValueProvider, ?>> type);
    
    public Collection<Class<? extends Calculation<? extends ValueProvider, ?>>> usedBy(Class<? extends Calculation<? extends ValueProvider, ?>> type);
}
