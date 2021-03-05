package il.ac.shenkar.project.costmanager.viewmodel;

import il.ac.shenkar.project.costmanager.model.*;
import il.ac.shenkar.project.costmanager.view.IView;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The ViewModel class is the concrete instance of the IViewModel interface.
 * The class have a direct access to the methods in both the View and the Model
 * portions of the MVVM architecture so that it functions as the conductor of
 * the Cost Manager application.
 */
public class ViewModel implements IViewModel {
    private IModel model;
    private IView view;
    public ExecutorService pool;

    /**
     * Sole constructor.
     * Creates an instance of a the ViewModel object and creates a thread pool for the
     * thread handling of the Cost Manager application.
     */
    public ViewModel() {
        pool = Executors.newFixedThreadPool(10);
    }

    /**
     * The method to set the View object so that the ViewModel has the connection needed to function.
     *
     * @param view the View object that is a concrete object of the IView type.
     */
    @Override
    public void setView(IView view) {
        this.view = view;
    }

    /**
     * The method to set the Model object so that the ViewModel has the connection needed to function.
     *
     * @param model the Model object that is a concrete object of the IModel type.
     */
    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

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
    @Override
    public void addCostItem(String description, double sum, Currency currency, String date, String categoryName) {
        // run the functionality of the action in new thread so as not to cause backup
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // create the category object and the cost item object
                    // id of the cost item is a dummy value at this point, the DB will assign a real id.
                    Category category = new Category(categoryName);
                    CostItem item = new CostItem(0, description, sum, currency, date, category);

                    // add the cost item object to the DB using the Model
                    model.addCostItem(item);

                    // on success display the good feedback to the user interface
                    view.showMessage("Cost item was added successfully");

                    // get the new list (updated) of cost items and display to user interface
                    CostItem[] items = model.getCostItems();
                    view.showItems(items, "all");

                } catch (CostManagerException e) {
                    // on fail display the bad feedback to the user interface
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    /**
     * The method to create a new category, the method is called upon by the user
     * and the ViewModel passes the request on to the Model to be executed and added to the DB.
     *
     * @param categoryName indicates the name of the category.
     */
    @Override
    public void addCategory(String categoryName) {
        // run the functionality of the action in new thread so as not to cause backup
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // create the category object
                    Category category = new Category(categoryName);

                    // add the category object to the DB using the Model
                    model.addCategory(category);

                    // on success display the good feedback to the user interface
                    view.showMessage("Category was added successfully");

                    // get the new list (updated) of categories and display to user interface
                    Category[] categories = model.getCategories();
                    view.showCategories(categories);

                } catch (CostManagerException e) {
                    // on fail display the bad feedback to the user interface
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    /**
     * The method to get all of the cost items from the DB, the method is called upon by the user
     * and the ViewModel passes the request on to the Model to be executed and brought from the DB.
     *
     * @param type the string representing if the cost items are intended to be displayed at
     *             the main table, at the table in the 'reports' frame or as a pie chart.
     */
    @Override
    public void getCostItems(String type) {
        // run the functionality of the action in new thread so as not to cause backup
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    CostItem[] items = model.getCostItems();
                    if (type.equals("pie")) {
                        Map<String, Double> map = new HashMap<>();
                        Category[] categories = model.getCategories();

                        for (Category category : categories) {
                            map.put(category.getName(), 0.0);
                        }

                        for (CostItem item : items) {
                            double oldVal = map.get(item.getCategory().getName());
                            map.replace(item.getCategory().getName(), oldVal + item.convertCurrencyToILS());
                        }
                        view.displayPieChart(map);
                    } else
                        view.showItems(items, type);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

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
    @Override
    public void getCostItems(Date date1, Date date2, String type) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    CostItem[] items = model.getCostItems(date1, date2);
                    if (type.equals("pie")) {
                        Map<String, Double> map = new HashMap<>();
                        Category[] categories = model.getCategories();

                        for (Category category : categories) {
                            map.put(category.getName(), 0.0);
                        }

                        for (CostItem item : items) {
                            double oldVal = map.get(item.getCategory().getName());
                            map.replace(item.getCategory().getName(), oldVal + item.convertCurrencyToILS());
                        }
                        view.displayPieChart(map);
                    } else
                        view.showItems(items, type);

                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    /**
     * The method to get all of the categories from the DB, the method is called upon by the user
     * and the ViewModel passes the request on to the Model to be executed and brought from the DB.
     */
    @Override
    public void getCategories() {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Category[] categories = model.getCategories();
                    view.showCategories(categories);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    /**
     * The method to delete a cost item from the DB, the method is called upon by the user
     * and the ViewModel passes the request on to the Model to be executed and deleted from the DB.
     *
     * @param id the id of the cost item to be deleted.
     */
    @Override
    public void deleteCostItem(int id) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.deleteCostItem(id);
                    view.showMessage("Cost item was deleted successfully");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items, "all");
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }
}