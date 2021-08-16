package generics.model;

/**
 * Type T is a placeholder, substituted at compile time.
 * You can override the definition of <T>. Check {@link Handler} for more info.
 * @param <T> A generics content.
 */
public class Crate<T> {
    private T contents;

    public T emptyCrate() {
        return contents;
    }
    public void packContents(T contents) {
        this.contents = contents;
    }
    public <T> T tricky(T t) { return t; } //new def of T, which hides the type parameter
    public <U> U readable(U t) { return t; } //U is used, instead of T
    public T alsoReadable(T t) { return t; } //Class T used
    public <U> boolean check(T t, U u) { return t == u; } //Both extends object
}
