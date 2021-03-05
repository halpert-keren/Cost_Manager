/**
 * @author Halpert, Keren (313604621)
 * @author Yechezkel, Danit Noa (203964036)
 */

package il.ac.shenkar.project.costmanager.view;

import il.ac.shenkar.project.costmanager.model.Category;
import il.ac.shenkar.project.costmanager.model.CostItem;
import il.ac.shenkar.project.costmanager.viewmodel.IViewModel;

import java.util.Map;

/**
 * The IView interface represents the view portion of the MVVM architecture structure of the Cost Manager.
 * The IView interface needs to be implemented by a concrete class
 * that will be used as the direct access and connection to the user in a user interface.
 */
public interface IView {

    /**
     * The method to set the ViewModel object so that the View has the connection needed to function.
     *
     * @param vm the ViewModel object that is a concrete object of the IViewModel type.
     */
    void setViewModel(IViewModel vm);

    /**
     * The method to display the pie chart of the cost items in the user interface.
     *
     * @param map the map representation of every category and its corresponding sum of expenses in it.
     */
    void displayPieChart(Map map);

    /**
     * The method to display a message in the user interface.
     *
     * @param text the string of the message to display in the user interface.
     */
    void showMessage(String text);

    /**
     * The method to display a list of cost item in the user interface.
     *
     * @param items the array of the cost items to display in the user interface.
     * @param type  the string representing if the list of cost items is intended to be
     *              displayed as the main table, as the table in the 'reports' frame or as a pie chart.
     */
    void showItems(CostItem[] items, String type);

    /**
     * The method to display the list of categories in the user interface.
     *
     * @param categories the array of the categories to display in the user interface.
     */
    void showCategories(Category[] categories);
}