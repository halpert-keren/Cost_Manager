/**
 * The DerbyDB class is used to connect and to correspond with the actual DB.
 * This class is implemented using a the syntax of Derby specific language.
 *
 * @param connection is an object of type Connection to be used to establish the connection the the DB
 * @param statement is an object of type Statement to be used for executing requests to the DB
 * @param rs is an object of type ResultSet ti be used for the data returned by the DB from a query
 */

package il.ac.shenkar.project.costmanager.model;

import java.sql.*;
import java.util.Date;

public class DerbyDBModel implements IModel {
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:";

    public DerbyDBModel() throws CostManagerException {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(driver);

            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();
            statement.execute("create table Category(" +
                    "id INT NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "name VARCHAR(50) UNIQUE)");
            statement.execute("create table CostItem(" +
                    "id INT NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "description VARCHAR(200), " +
                    "\"sum\" FLOAT, " +
                    "date DATE, " +
                    "category VARCHAR(50), " +
                    "FOREIGN KEY (category) REFERENCES Category(name))");

        } catch (ClassNotFoundException | SQLException e) {
            throw new CostManagerException(e.getMessage(), e.getCause());

        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (Exception e) {
                    throw new CostManagerException(e.getMessage(), e.getCause());

                }
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
                    throw new CostManagerException(e.getMessage(), e.getCause());

                }
        }
    }

    @Override
    public void addCostItem(CostItem item) throws CostManagerException {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(driver);

            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO " + item.getCategory() + " (DESCRIPTION, PRICE, CURRENCY, DATE) VALUES('" + item.getDescription() + "','" + item.getSum() + "','" + item.getCurrency() + "','" + item.getDate() + "')");

        } catch (ClassNotFoundException | SQLException e) {
            throw new CostManagerException(e.getMessage(), e.getCause());

        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (Exception e) {
                    throw new CostManagerException(e.getMessage(), e.getCause());

                }
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
                    throw new CostManagerException(e.getMessage(), e.getCause());

                }
        }
    }

    @Override
    public void addCategory(Category category) throws CostManagerException {

    }

    @Override
    public CostItem[] getCostItems() throws CostManagerException {
        return new CostItem[0];
    }

    @Override
    public CostItem[] getCostItems(Date date1, Date date2) throws CostManagerException {
        return new CostItem[0];
    }

    @Override
    public Category[] getCategories() throws CostManagerException {
        return new Category[0];
    }

    @Override
    public void deleteCostItem(CostItem item) throws CostManagerException {

    }
}