package il.ac.shenkar.project.costmanager.viewmodel;

import il.ac.shenkar.project.costmanager.model.*;
import il.ac.shenkar.project.costmanager.view.IView;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {
    private IModel model;
    private IView view;
    public ExecutorService pool;

    public ViewModel() {
        pool = Executors.newFixedThreadPool(10);
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void addCostItem(String description, double sum, Currency currency, String date, String categoryName) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Category category = new Category(categoryName);
                    CostItem item = new CostItem(0, description, sum, currency, date, category);
                    model.addCostItem(item);
                    view.showMessage("Cost item was added successfully");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items, "all");
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    @Override
    public void addCategory(String categoryName) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Category category = new Category(categoryName);
                    model.addCategory(category);
                    view.showMessage("Category was added successfully");
                    Category[] categories = model.getCategories();
                    view.showCategories(categories);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getCostItems(String type) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    CostItem[] items = model.getCostItems();
                    if(type.equals("pie")) {
                        Map<String, Double> map = new HashMap<>();
                        Category[] categories = model.getCategories();

                        for (Category category: categories){
                            map.put(category.getName(), 0.0);
                        }

                        for (CostItem item: items){
                            double oldVal = map.get(item.getCategory().getName());
                            map.replace(item.getCategory().getName(), oldVal + item.convertCurrencyToILS());
                        }
                        view.displayPieChart(map);
                    }else
                        view.showItems(items, type);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getCostItems(Date date1, Date date2, String type) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    CostItem[] items = model.getCostItems(date1, date2);
                    if(type.equals("pie")) {
                        Map<String, Double> map = new HashMap<>();
                        Category[] categories = model.getCategories();

                        for (Category category: categories){
                            map.put(category.getName(), 0.0);
                        }

                        for (CostItem item: items){
                            double oldVal = map.get(item.getCategory().getName());
                            map.replace(item.getCategory().getName(), oldVal + item.convertCurrencyToILS());
                        }
                        view.displayPieChart(map);
                    }
                    else
                        view.showItems(items, type);

                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

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