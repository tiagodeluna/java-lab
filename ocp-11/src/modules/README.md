# Instructions to Run this Example

## 1. Compiling the First Module (Feeding)

```
javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/*.java
```

* `-p` or `--module-path`: indicates the location of the custom module files, if there are any.
* `-d`: specifies the directory to place the class files.

## 2. Running the First Module

```
java -p feeding --module zoo.animal.feeding/zoo.animal.feeding.Task
```

* `--module` or `m`: used to specify the module name and the fully qualified class name to be executed, in the format: `<module-name>/<class-name>`.

## 3. Packaging the First Module

```
jar -cvf mods/zoo.animal.feeding.jar -C feeding/ .
```

* `-c` or `--create`: creates a new JAR file
* `-v` or `--verbose`: prints details when working with JAR files
* `-f` or `--file`: JAR filename
* `-C`: Specifies the directory containing the files to be added to the JAR

## 4. Compiling and Packaging the Second Module (Care)

```
javac -p mods -d care care/zoo/animal/care/details/*.java care/zoo/animal/care/medical/*.java care/*.java

jar -cvf mods/zoo.animal.care.jar -C care/ .
```

# 5. Compiling and Packagin the Third Module (Talks)

```
javac -p mods -d talks talks/zoo/animal/talks/content/*.java talks/zoo/animal/talks/media/*.java talks/zoo/animal/talks/schedule/*.java talks/*.java

jar -cvf mods/zoo.animal.talks.jar -C talks/ .
```

# 6. Compiling and Packagin the Forth Module (Staff)

```
javac -p mods -d staff staff/zoo/staff/*.java staff/*.java

jar -cvf mods/zoo.staff.jar -C staff/ .
```

## X. Running the packaged Modules

```
java -p mods -m zoo.animal.feeding/zoo.animal.feeding.Task
```

_Note: in this case, we are pointing to the module-path "mods", where the JAR file was created._

