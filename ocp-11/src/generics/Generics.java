package generics;

import generics.model.Crate;
import generics.model.Handler;

/**
 * See the model classes {@link Crate} and {@link Handler} for more information.
 */
public class Generics {

    public static void main(String... args) {
        var handler = new Handler();
        Integer i1 = handler.doSomething(1);
        Crate<String> crate = handler.ship("text");

        Integer i2 = crate.tricky(2);
        String s1 = crate.readable("text1");
        s1 = crate.<String>alsoReadable("text2"); //You don't need to, but you can specify the type
    }

}
