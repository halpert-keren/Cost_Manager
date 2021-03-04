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
import java.sql.Date;

public class DerbyDBModel implements IModel {
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:CostManager;create=true";

    public DerbyDBModel() throws CostManagerException {
        Connection connection = null;
        Statement statement = null;

        try {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new CostManagerException(e.getMessage(), e.getCause());
            }

            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();
            statement.execute("create table Category(" +
                    "id INT NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "name VARCHAR(50) UNIQUE)");
            statement.execute("create table CostItem(" +
                    "id INT NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "description VARCHAR(200), " +
                    "price FLOAT, " +
                    "currency VARCHAR(5), " +
                    "date_ DATE, " +
                    "category VARCHAR(50), " +
                    "FOREIGN KEY (category) REFERENCES Category(name))");

        } catch (SQLException e) {
            if ("X0Y32".equals(e.getSQLState())) {
                System.out.println("Table already exists");
            } else
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

            statement.executeUpdate("INSERT INTO CostItem (description, price, currency, date_, category) VALUES('" + item.getDescription() + "'," + item.getSum() + ",'" + item.getCurrency().toString() + "','" + item.getDate() + "','" + item.getCategory().getName() + "')");
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
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = null;
            Class.forName(driver);

            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT * FROM Category");
            while (rs.next()) {
                if (rs.getString("name").equals(category.getName())) {
                    throw new CostManagerException("category already exists.");
                }
            }
            statement.execute("INSERT INTO Category (NAME) VALUES ('" + category.getName() + "')");

        } catch (ClassNotFoundException | SQLException e) {
            throw new CostManagerException(e.getMessage(), e.getCause());
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (Exception e) {
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
                }
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
        }

    }

    /**
     * This method return array of all Categories objects
     */
    @Override
    public Category[] getCategories() throws CostManagerException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);

            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT * FROM Category");
            int count = 0;
            while (rs.next()) {
                count++;
            }
            Category[] categories = new Category[count];
            rs = statement.executeQuery("SELECT * FROM Category");
            int i = 0;
            while (rs.next()) {
                categories[i] = new Category(rs.getString("name"));
                i++;
            }
            return categories;

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

    /**
     * This method return array of all CostItem objects
     */
    @Override
    public CostItem[] getCostItems() throws CostManagerException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT * FROM CostItem");
            int count = 0;
            while (rs.next()) {
                count++;
            }
            CostItem[] costItems = new CostItem[count];
            rs = statement.executeQuery("SELECT * FROM CostItem");
            int i = 0;
            while (rs.next()) {
                costItems[i] = new CostItem(rs.getInt("id") ,rs.getString("description"), rs.getDouble("price"), Currency.valueOf(rs.getString("currency")), rs.getDate("date_"), new Category(rs.getString("category")));
                i++;
            }
            return costItems;
        } catch (ClassNotFoundException | SQLException e) {
            throw new CostManagerException("problem with getting cost data from derbyDB", e.getCause());
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

    /**
     * This method return cost items between tow dates
     */
    @Override
    public CostItem[] getCostItems(Date dateStart, Date dateEnd) throws CostManagerException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT * FROM CostItem WHERE date_ BETWEEN DATE('" + dateStart + "') and DATE('" + dateEnd + "')");
            int count = 0;
            while (rs.next()) {
                count++;
            }
            CostItem[] costItems = new CostItem[count];
            rs = statement.executeQuery("SELECT * FROM CostItem WHERE date_ BETWEEN DATE('" + dateStart + "') and DATE('" + dateEnd + "')");
            int i = 0;
            while (rs.next()) {
                costItems[i] = new CostItem(rs.getInt("id"), rs.getString("description"), rs.getDouble("price"), Currency.valueOf(rs.getString("currency")), rs.getDate("date_"), new Category(rs.getString("category")));
                i++;
            }
            return costItems;

        } catch (ClassNotFoundException | SQLException e) {
            throw new CostManagerException(e.getMessage(), e.getCause());

        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (Exception e) {
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
                }
        }

    }

    /**
     * This method deleted cost items from array items objects
     */
    @Override
    public void deleteCostItem(int id) throws CostManagerException {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(driver);

            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM CostItem WHERE id =" + id);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new CostManagerException("problem to delete cost item", e.getCause());

        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (Exception e) {
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
                }
        }
    }
}