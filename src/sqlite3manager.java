
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author James Wirth
 */
public class sqlite3manager {
    public List<Map<Object, Object>> list(String query, String databaseURL) throws SQLException {
        
    //Create new array list of string & object key value pairs (returned from database)
    List<Map<Object, Object>> result = new ArrayList<Map<Object, Object>>();
    
    //Initialise empty key value pair containing a string and an object
    Map<Object, Object> row = null;
    
    try (Connection conn = this.connect(databaseURL);
                PreparedStatement statement = conn.prepareStatement(query)) {
        
        ResultSet resultSet = statement.executeQuery();
        
        //Get number of columns in database so we know how long the list of key value pairs has to be for each row
        ResultSetMetaData metaData = resultSet.getMetaData();
        Integer columnCount = metaData.getColumnCount();

        //For each row in resultSet
        while (resultSet.next()) {
            row = new HashMap<Object, Object>();
            //For each column in database (more specifically the resultSet)
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            //Add the hashmap object to the result list
            result.add(row);
        }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    return result;
    }
    
    public void runQuery(String query, String databaseURL) {
        try (Connection conn = this.connect(databaseURL);
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private Connection connect(String databaseURL) {
        String url = databaseURL;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
