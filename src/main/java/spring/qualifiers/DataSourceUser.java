package spring.qualifiers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class DataSourceUser {
    @Inject
//    @Frontend
    DataSource frontend;
    
    @Inject
    @DataWarehouse 
    DataSource dataWarehouse;
    
    public void printDb() {
        printDb(frontend);
        printDb(dataWarehouse);
    }

    private void printDb(DataSource ds) {
        try {
            PreparedStatement statement = ds.getConnection().prepareStatement("select DATABASE()");
            ResultSet rs = statement.executeQuery();
            rs.next();
            System.out.println(rs.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
