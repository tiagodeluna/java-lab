package generics.model;

/**
 * Generics can be used in the method definition as well (see below).
 * And you can override the definition of <T>.
 */
public class Handler {

    public <T> void prepare(T t) {}
    public <T> Crate<T> ship(T t) { return new Crate<>(); }
    public <T> T doSomething(T t) { return t; }
    //public <T> doesNotCompile() { return null; } //Missing return type
    //public T alsoDoesNotCompile() { return null; } //Missing <T>
    public <T> T doSomethingElse(T t) { return doSomething(t); }
}
