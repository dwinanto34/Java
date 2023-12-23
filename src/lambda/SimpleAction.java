package lambda;

// Lambda is a simple version of anonymous class
// Lambda is an interface
// Must add @FunctionalInterface annotation
@FunctionalInterface
public interface SimpleAction {
    // only one abstract method is allowed here
    String action(String name);
}
