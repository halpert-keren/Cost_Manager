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
import java.util.ArrayList;
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
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = null;
            Class.forName(driver);

            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT NAME FROM Category");

            while (rs.next()) {
                if (rs.getString(1).equals(category.getName())) {
                    throw new CostManagerException("category already exists.");
                }
            }

            statement.execute("INSERT INTO Category (NAME) VALUES ('" + category.getName() + "')");
            statement.execute("CREATE TABLE " + category.getName() + " (price VARCHAR(30), currency VARCHAR(10), description VARCHAR(100), date TIMESTAMP) ");

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

        try {
            Class.forName(driver);

            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();

//            statement.executeUpdate("INSERT INTO " + item.getCategory() + " (DESCRIPTION, PRICE, CURRENCY, DATE) VALUES('" + item.getDescription() + "','" + item.getSum() + "','" + item.getCurrency() + "','" + item.getDate() + "')");

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
        ResultSet result = null;


        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();
//            String query = "SELECT * FROM CostItem WHERE Category = '" + Category + "'";

            CostItem[] arrCost = new CostItem[];
            try {
                result = statement.executeQuery(query);
                while (result.next()) {
                    arrCost.add(new CostItem(result.getInt(1), result.getDate(2), result.getString(3), result.getString(4), result.getDouble(5), result.getString(6)));
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new CostManagerException("problem with getting cost data from derbyDB", e.getCause());

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
         * This method return cost items between tow dates
         */
        @Override
        public CostItem[] getCostItems (Date dateStart, Date dateEnd) throws CostManagerException {

            Connection connection = null;
            Statement statement = null;

            try {
                connection = null;
                Class.forName(driver);

                connection = DriverManager.getConnection(protocol);
                statement = connection.createStatement();
                return ("SELECT * FROM costs WHERE date_ BETWEEN DATE('" + dateStart.toLocalDate() + "') and DATE('" + dateEnd.toLocalDate() + "')");


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
        public void deleteCostItem (int id) throws CostManagerException {
            Connection connection = null;
            Statement statement = null;

            try {
                connection = null;
                Class.forName(driver);

                connection = DriverManager.getConnection(protocol);
                statement = connection.createStatement();
                String query = "DELETE FROM CostItem WHERE id =" + id;

            } catch (ClassNotFoundException | SQLException e) {
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