package il.ac.shenkar.project.costmanager.viewmodel;

import il.ac.shenkar.project.costmanager.model.Category;
import il.ac.shenkar.project.costmanager.model.CostItem;
import il.ac.shenkar.project.costmanager.model.CostManagerException;
import il.ac.shenkar.project.costmanager.model.IModel;
import il.ac.shenkar.project.costmanager.view.IView;

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
    public void addCostItem(CostItem item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCostItem(item);
                    view.showMessage("Cost item was added successfully");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    @Override
    public void addCategory(Category category) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
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
    public void getCostItems() {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    CostItem[] items = model.getCostItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getCostItems(String date1, String date2) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    CostItem[] items = model.getCostItems(date1, date2);
                    view.showItems(items);
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
    public void deleteCostItem(CostItem item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.deleteCostItem(item);
                    view.showMessage("Cost item was deleted successfully");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }
}