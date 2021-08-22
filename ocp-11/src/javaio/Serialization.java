package javaio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Serialization {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var xmen = new ArrayList<Superhuman>();
        xmen.add(new Superhuman("Jean Grey", 35, 'F', 'A'));
        xmen.add(new Superhuman("Cyclope", 38, 'M', 'B'));
        File dataFile = new File("out/xmen.txt");
        serialize(xmen, dataFile);
        System.out.println("The following data was stored: \n" + xmen);

        var xmenFromFile = deserialize(dataFile);
        System.out.println("The following data was retrieved: \n" + xmenFromFile);
    }

    static void serialize(List<Superhuman> list, File dataFile) throws IOException {
        try (var out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(dataFile)))) {
            for (var superhuman : list) {
                out.writeObject(superhuman);
            }
        }
    }

    static List<Superhuman> deserialize(File dataFile) throws IOException, ClassNotFoundException {
        var list = new ArrayList<Superhuman>();
        try(var in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(dataFile)))) {
            /* ObjectInputStream does not return -1 neither null when file reaches the end. Instead, it throws
             * EOFException, so we have to use an infinite loop and catch the exception to proceed. */
            while(true) {
                var object = in.readObject();
                list.add((Superhuman) object);
            }
        } catch (EOFException e) {
            System.out.println("File end reached");
        }

        return list;
    }

    /** When Serializing, only (non-null) instance members of the class are serialized. When deserializing, Java will
     * call the no-arg constructor of the first nonserializale parent class and use it to instantiate the class.
        See below how the fields are handled in the serialization/deserialization.
     */
    static class Superhuman implements Serializable {
        // serialVersionUID is stored and used as a reference for the object/class version
        private static final long serialVersionUID = 1L;
        // Normal instance fields are serialized/deserialized (even without getters and setters)
        private String name;
        private final char powerLevel; // value of powerLevel is set in the initializer block below
        /* Transient fields are not serialized. When deserializing, the default value of each type is assigned
            (e.g int=0, double=0.0, char=<space>, Object=null, etc.) */
        private transient char gender = 'M';
        private transient int age = 25;
        /* static  */
        private static char type = 'C';
        { powerLevel = '1'; }

        /** This constructor is ignored by the Deserialization process. Object's no-args constructor is used. */
        public Superhuman() {
            this.name = "Logan";
            this.age = 50;
            this.gender = 'M';
            this.type = 'B';
        }

        public Superhuman(String name, int age, char gender, char type) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.type = type;
        }

        @Override
        public String toString() {
            return String.format("[name: %s, powerLevel: %s, gender: %s, age: %d, type: %s]",
                    name, powerLevel, gender, age, type);
        }
    }
}

