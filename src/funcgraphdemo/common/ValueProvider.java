package funcgraphdemo.common;

public interface ValueProvider {

    public <T> T get(Class<? extends Calculation<? extends ValueProvider, T>> type);

    public <T> void set(Class<? extends Calculation<? extends ValueProvider, T>> type, T value);
}
