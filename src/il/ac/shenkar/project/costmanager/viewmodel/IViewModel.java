/**
 * @author Halpert, keren (313604621)
 * @author Yechezkel, Danit Noa (203964036)
 */

package il.ac.shenkar.project.costmanager.viewmodel;

import il.ac.shenkar.project.costmanager.model.Currency;
import il.ac.shenkar.project.costmanager.model.IModel;
import il.ac.shenkar.project.costmanager.view.IView;

import java.sql.Date;

/**
 * The IViewModel interface represents the view-model portion of the MVVM architecture structure of the Cost Manager.
 * The IViewModel interface needs to be implemented by a concrete class
 * that will be used as the director between the Model (and the DB) and the View (and the user).
 */
public interface IViewModel {

    /**
     * The method to set the View object so that the ViewModel has the connection needed to function.
     *
     * @param view the View object that is a concrete object of the IView type.
     */
    void setView(IView view);

    /**
     * The method to set the Model object so that the ViewModel has the connection needed to function.
     *
     * @param model the Model object that is a concrete object of the IModel type.
     */
    void setModel(IModel model);

    /**
     * The method to create a new cost item, the method is called upon by the user
     * and the ViewModel passes the request on to the Model to be executed and added to the DB.
     *
     * @param description  indicates the description of the cost item.
     * @param sum          indicates the expense/price of the cost item.
     * @param currency     indicates the currency of the cost item.
     * @param date         indicates the date in which the expense was made.
     * @param categoryName indicates category that the cost item belongs to.
     */
    void addCostItem(String description, double sum, Currency currency, String date, String categoryName);

    /**
     * The method to create a new category, the method is called upon by the user
     * and the ViewModel passes the request on to the Model to be executed and added to the DB.
     *
     * @param categoryName indicates the name of the category.
     */
    void addCategory(String categoryName);

    /**
     * The method to get all of the cost items from the DB, the method is called upon by the user
     * and the ViewModel passes the request on to the Model to be executed and brought from the DB.
     *
     * @param type the string representing if the cost items are intended to be displayed at
     *             the main table, at the table in the 'reports' frame or as a pie chart.
     */
    void getCostItems(String type);

    /**
     * The method to get the cost items from the DB that are between two dates,
     * the method is called upon by the user and the ViewModel passes the
     * request on to the Model to be executed and brought from the DB.
     *
     * @param date1 the date representing the beginning of the time period between both dates.
     * @param date2 the date representing the end of the time period between both dates.
     * @param type  the string representing if the cost items are intended to be displayed at
     *              the main table, at the table in the 'reports' frame or as a pie chart.
     */
    void getCostItems(Date date1, Date date2, String type);

    /**
     * The method to get all of the categories from the DB, the method is called upon by the user
     * and the ViewModel passes the request on to the Model to be executed and brought from the DB.
     */
    void getCategories();

    /**
     * The method to delete a cost item from the DB, the method is called upon by the user
     * and the ViewModel passes the request on to the Model to be executed and deleted from the DB.
     *
     * @param id the id of the cost item to be deleted.
     */
    void deleteCostItem(int id);
}