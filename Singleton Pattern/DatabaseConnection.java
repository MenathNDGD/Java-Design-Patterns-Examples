public class DatabaseConnection {
    private static DatabaseConnection instance;
    
    private DatabaseConnection() {
        System.out.println("Database Connection established");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void query(String sql) {
        System.out.println("Executing query: " + sql);
    }

    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        
        db1.query("SELECT * FROM users");
        db2.query("INSERT INTO users VALUES (1, 'John')");
        
        System.out.println(db1 == db2);
    }
}
