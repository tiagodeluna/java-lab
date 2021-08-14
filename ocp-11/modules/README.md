# Instructions to Run this Example

### 1. Compiling the First Module (Feeding)

```
javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/*.java
```

* `-p` or `--module-path`: indicates the location of the custom module files, if there are any.
* `-d`: specifies the directory to place the class files.

### 2. Running the First Module

```
java -p feeding --module zoo.animal.feeding/zoo.animal.feeding.Task
```

* `--module` or `m`: used to specify the module name and the fully qualified class name to be executed, in the format: `<module-name>/<class-name>`.

### 3. Packaging the First Module

```
jar -cvf mods/zoo.animal.feeding.jar -C feeding/ .
```

* `-c` or `--create`: creates a new JAR file
* `-v` or `--verbose`: prints details when working with JAR files
* `-f` or `--file`: JAR filename
* `-C`: Specifies the directory containing the files to be added to the JAR

### 4. Compiling and Packaging the Second Module (Care)

```
javac -p mods -d care care/zoo/animal/care/details/*.java care/zoo/animal/care/medical/*.java care/*.java

jar -cvf mods/zoo.animal.care.jar -C care/ .
```

### 5. Compiling and Packaging the Third Module (Talks)

```
javac -p mods -d talks talks/zoo/animal/talks/content/*.java talks/zoo/animal/talks/media/*.java talks/zoo/animal/talks/schedule/*.java talks/*.java

jar -cvf mods/zoo.animal.talks.jar -C talks/ .
```

### 6. Compiling and Packaging the Forth Module (Staff)

```
javac -p mods -d staff staff/zoo/staff/*.java staff/*.java

jar -cvf mods/zoo.staff.jar -C staff/ .
```

### 7. Running s Packaged Module

```
java -p mods -m zoo.animal.feeding/zoo.animal.feeding.Task
```

_Note[1]: in this case, we are pointing to the module-path "mods", where the JAR file was created._

_Note[2]: you can add `--show-module-resolution` when running the `java` command to display a lot of extra information before running the application, such as: packages included, modules that have dependencies and so on._


### 8. Describing a Module

```
java -p mods -d zoo.animal.care
```

* `-d` or `--describe-module`: prints information about the module.

_Note[1]: the line `requires java.base mandated` printed in the output refers to the java.base module, which is automatically added as a dependency to all modules._

_Note[2]: `contains zoo.animal.care.details` means that this package is not exported, and is available only to code inside the module._

_Note[3]: the jar command `jar -f mods/zoo.animal.care.jar -d` prints almost the same thing._


### 9. Listing Modules

```
java -p mods --list-modules
```

_Note: the command `java --list-modules` (without reference to a custom module-path) will list only the modules that are part of the JDK._


### 10. Listing Module Dependencies

The `jdeps` command looks at the code in addition to the module-info file to tell what dependencies are actually used by the module.

```
jdeps -s --module-path mods mods/zoo.animal.care.jar
```

* `-s` or `-summary`: lists only the dependencies used in the module. If you remove this option, the full description will be printed.
* `--module-path`: there is not a short form of this option in the jdeps command.
