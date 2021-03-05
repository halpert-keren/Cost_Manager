package il.ac.shenkar.project.costmanager.viewmodel;

import il.ac.shenkar.project.costmanager.model.Category;
import il.ac.shenkar.project.costmanager.model.CostItem;
import il.ac.shenkar.project.costmanager.model.Currency;
import il.ac.shenkar.project.costmanager.model.IModel;
import il.ac.shenkar.project.costmanager.view.IView;

import java.sql.Date;

public interface IViewModel {
    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(String description, double sum, Currency currency, String date, String category);
    public void addCategory(String categoryName);
    public void getCostItems(String type);
    public void getCostItems(Date date1, Date date2, String type);
    public void getCategories();
    public void deleteCostItem(int id);
}