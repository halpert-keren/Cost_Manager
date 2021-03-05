/**
 * @author Halpert, Keren (313604621)
 * @author Yechezkel, Danit Noa (203964036)
 */

package il.ac.shenkar.project.costmanager.model;

import java.sql.Date;

/**
 * The IModel interface represents the model portion of the MVVM architecture structure of the Cost Manager.
 * The IModel interface needs to be implemented by a concrete class
 * that will be used as the direct access and connection to the DB.
 */
public interface IModel {

    /**
     * The method to add a new cost item to the DB.
     * Opens a DB connection and adds the new CostItem, then closes the connection.
     *
     * @param item the CostItem object to be added to the DB.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    void addCostItem(CostItem item) throws CostManagerException;

    /**
     * The method to add a new category to the DB.
     * Opens a DB connection and adds the new Category, then closes the connection.
     *
     * @param category the Category object to be added to the DB.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    void addCategory(Category category) throws CostManagerException;

    /**
     * The method to get all categories in the DB.
     *
     * @return the array of Category objects.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    Category[] getCategories() throws CostManagerException;

    /**
     * The method to get all cost items in the DB.
     *
     * @return the array of CostItem objects.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    CostItem[] getCostItems() throws CostManagerException;

    /**
     * The method to get all cost items in the DB that are between two dates.
     *
     * @param dateStart the date representing the beginning of the time period between both dates.
     * @param dateEnd   the date representing the end of the time period between both dates.
     * @return the array of Category objects.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    CostItem[] getCostItems(Date dateStart, Date dateEnd) throws CostManagerException;

    /**
     * The method to delete a cost item from the DB.
     *
     * @param id the id of the cost item to be deleted.
     * @throws CostManagerException if could not execute DB query
     *                              or could not open or close the DB connection.
     */
    void deleteCostItem(int id) throws CostManagerException;
}