package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * For this code to compile and run properly, you must install the H2 database and add the .jar to your classpath.
 */
public class JDBCBasics {
    private static final String INSERT_SQL = "INSERT INTO animal VALUES (?, ?, ?)";
    private static final List<Animal> animals = List.of(
            new Animal(1, "Lion", 'A'),
            new Animal(2, "Giraffe", 'C'),
            new Animal(3, "Snake", 'B')
    );

    public static void main(String[] args) throws SQLException {
        try (var conn = getConnection()) {
            createAndPopulateTable(conn);
            selectUsingPrepareStatement(conn);
        }
    }

    static Connection getConnection() {
        System.out.println("\nCreating connection to DB...");
        Connection conn = null;
        try {
            //Class.forName("org.h2.Driver");
            // The JDBC url is always composed by <protocol>:<subprotocol>:<subname or database-name>
            final String url = "jdbc:h2:~/test";
            // In real-world scenarios, we should avoid using DriverManager and use DataSource instead
            conn = DriverManager.getConnection(url,"sa","");
            System.out.println(conn);
        } catch (SQLException e) {
            System.err.println("Failed to create connection");
        }

        return conn;
    }


    private static void selectUsingPrepareStatement(Connection conn) throws SQLException {
        System.out.println("\nRetrieving data from ANIMALS table...");
        try (var ps = conn.prepareStatement("SELECT * FROM animal")) {
            ResultSet rs = ps.executeQuery();
            System.out.println("Animals found : ");
            while(rs.next()) {
                System.out.format("  ID = %s, NAME = %s, TYPE = %s\n",
                        rs.getInt("id"), rs.getString("name"), rs.getString("type"));
            }
        }
    }

    static void createAndPopulateTable(Connection conn) {
        System.out.println("\nCreating and populating ANIMALS table...");
        try (Statement stmt = conn.createStatement()) {
            // Drop existing table
            String dropTable = "DROP TABLE animal";
            stmt.execute(dropTable);

            // Create new table
            String createTable = "CREATE TABLE animal " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " type VARCHAR(1), " +
                    " PRIMARY KEY ( id ))";
            stmt.execute(createTable);

            for(var animal : animals) {
                insertAnimal(conn, animal);
            }

            System.out.println("Created table in test database...");
        } catch (SQLException e) {
            System.err.println("Failed to close the connection: ");
            e.printStackTrace();
        }
    }

    static void insertAnimal(Connection conn, Animal animal) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setInt(1, animal.getId());
            ps.setString(2, animal.getName());
            ps.setString(3, String.valueOf(animal.getType()));
            int rs = ps.executeUpdate();
            System.out.println("Rows affected : " + rs);
        }
    }

    static class Animal {
        private int id;
        private String name;
        private char type;

        public Animal(int id, String name, char type) {
            this.id = id;
            this.name = name;
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public char getType() {
            return type;
        }
    }
}
