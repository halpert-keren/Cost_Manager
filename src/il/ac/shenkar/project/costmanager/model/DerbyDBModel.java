package il.ac.shenkar.project.costmanager.model;

import java.sql.*;

/**
 * The DerbyDBModel class is used to connect and to correspond with the actual DB.
 * This class is implemented using a the syntax of Derby specific language (SQL).
 */
public class DerbyDBModel implements IModel {
    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String protocol = "jdbc:derby:CostManager;create=true";

    /**
     * Sole constructor.
     * Opens a DB connection and creates the tables (if they don't exist already), then closes the connection.
     *
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    public DerbyDBModel() throws CostManagerException {
        Connection connection = null;
        Statement statement = null;

        try {
            // open DB connection and create statement
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new CostManagerException(e.getMessage(), e.getCause());
            }
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            // execute SQL query - create tables
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
            } else
                throw new CostManagerException(e.getMessage(), e.getCause());
        } finally {
            // close statement and DB connection
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
     * The method to add a new cost item to the DB.
     * Opens a DB connection and adds the new CostItem, then closes the connection.
     *
     * @param item the CostItem object to be added to the DB.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    @Override
    public void addCostItem(CostItem item) throws CostManagerException {
        Connection connection = null;
        Statement statement = null;

        try {
            // open DB connection and create statement
            Class.forName(driver);
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            // execute SQL query - add new cost item
            statement.executeUpdate("INSERT INTO CostItem (description, price, currency, date_, category) VALUES('" + item.getDescription() + "'," + item.getSum() + ",'" + item.getCurrency().toString() + "','" + item.getDate() + "','" + item.getCategory().getName() + "')");

        } catch (ClassNotFoundException | SQLException e) {
            throw new CostManagerException(e.getMessage(), e.getCause());
        } finally {
            // close statement and DB connection
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
     * The method to add a new category to the DB.
     * Opens a DB connection and adds the new Category, then closes the connection.
     *
     * @param category the Category object to be added to the DB.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    @Override
    public void addCategory(Category category) throws CostManagerException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // open DB connection and create statement
            Class.forName(driver);
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            // check if category exists already and execute SQL query - add new category
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
            // close statement and DB connection
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
     * The method to get all categories in the DB.
     *
     * @return the array of Category objects.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    @Override
    public Category[] getCategories() throws CostManagerException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // open DB connection and create statement
            Class.forName(driver);
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            // execute SQL query - get categories and create return array
            rs = statement.executeQuery("SELECT * FROM Category");
            int count = 0;
            while (rs.next()) count++;
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
            // close statement and DB connection
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
     * The method to get all cost items in the DB.
     *
     * @return the array of CostItem objects.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    @Override
    public CostItem[] getCostItems() throws CostManagerException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // open DB connection and create statement
            Class.forName(driver);
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            // execute SQL query - get cost items and create return array
            rs = statement.executeQuery("SELECT * FROM CostItem");
            int count = 0;
            while (rs.next()) count++;
            CostItem[] costItems = new CostItem[count];
            rs = statement.executeQuery("SELECT * FROM CostItem");
            int i = 0;
            while (rs.next()) {
                costItems[i] = new CostItem(rs.getInt("id"), rs.getString("description"), rs.getDouble("price"), Currency.valueOf(rs.getString("currency")), rs.getString("date_"), new Category(rs.getString("category")));
                i++;
            }
            return costItems;
        } catch (ClassNotFoundException | SQLException e) {
            throw new CostManagerException(e.getMessage(), e.getCause());
        } finally {
            // close statement and DB connection
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
     * The method to get all cost items in the DB that are between two dates.
     *
     * @param dateStart the date representing the beginning of the time period between both dates.
     * @param dateEnd   the date representing the end of the time period between both dates.
     * @return the array of Category objects.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    @Override
    public CostItem[] getCostItems(Date dateStart, Date dateEnd) throws CostManagerException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // open DB connection and create statement
            Class.forName(driver);
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            // execute SQL query - get cost items and create return array
            rs = statement.executeQuery("SELECT * FROM CostItem WHERE date_ BETWEEN DATE('" + dateStart + "') and DATE('" + dateEnd + "')");
            int count = 0;
            while (rs.next()) count++;
            CostItem[] costItems = new CostItem[count];
            rs = statement.executeQuery("SELECT * FROM CostItem WHERE date_ BETWEEN DATE('" + dateStart + "') and DATE('" + dateEnd + "')");
            int i = 0;
            while (rs.next()) {
                costItems[i] = new CostItem(rs.getInt("id"), rs.getString("description"), rs.getDouble("price"), Currency.valueOf(rs.getString("currency")), rs.getString("date_"), new Category(rs.getString("category")));
                i++;
            }
            return costItems;

        } catch (ClassNotFoundException | SQLException e) {
            throw new CostManagerException(e.getMessage(), e.getCause());
        } finally {
            // close statement and DB connection
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
     * The method to delete a cost item from the DB.
     *
     * @param id the id of the cost item to be deleted.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    @Override
    public void deleteCostItem(int id) throws CostManagerException {
        Connection connection = null;
        Statement statement = null;

        try {
            // open DB connection and create statement
            Class.forName(driver);
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            // execute SQL query - delete from DB
            statement.executeUpdate("DELETE FROM CostItem WHERE id =" + id);

        } catch (ClassNotFoundException | SQLException e) {
            throw new CostManagerException(e.getMessage(), e.getCause());
        } finally {
            // close statement and DB connection
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
}