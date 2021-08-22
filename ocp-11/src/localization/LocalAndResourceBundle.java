package localization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalAndResourceBundle {

    public static void main(String[] args) {

        System.out.println("USING RESOURCE BUNDLE WITH DIFFERENT LOCALES");
        // Gets the default locale which happens to be en_US (in my case at least)
        System.out.println("Using resource bundle with default locale: " + Locale.getDefault());
        var rb = ResourceBundle.getBundle("mybundle");
        System.out.println("> " + rb.getString("hello"));
        // Since 'bye' is not defined in the specific "en_US" props file, it is taken from the default props (w/t locale)
        System.out.println("> " + rb.getString("bye"));

        // Defines a custom locale using constructor
        Locale currentLocal = new Locale("en", "NL");
        System.out.println("Using resource bundle with my current locale: " + currentLocal);
        rb = ResourceBundle.getBundle("mybundle", currentLocal);
        System.out.println("> " + rb.getString("hello"));
        System.out.println("> " + rb.getString("bye"));

        // Defines custom locale using the Builder available
        Locale myLocale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
        System.out.println("Using resource bundle with my natural locale: " + myLocale);
        rb = ResourceBundle.getBundle("mybundle", myLocale);
        System.out.println("> " + rb.getString("hello"));
        System.out.println("> " + rb.getString("bye"));

        // Uses a pre-defined locale
        System.out.println("Using resource bundle with a non-configured locale: " + Locale.GERMANY);
        rb = ResourceBundle.getBundle("mybundle", Locale.GERMANY);
        // Since de_DE bundle is not defined, it first looks to the default locale props, and then to the default props
        System.out.println("> " + rb.getString("hello"));
        System.out.println("> " + rb.getString("bye"));

        //Prints a formatted message using MessageFormat and the bundle properties
        System.out.println(MessageFormat.format("> " + rb.getString("quickgreeting"), rb.getString("hello"), rb.getString("bye")));
    }
}
