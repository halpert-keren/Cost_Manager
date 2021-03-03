package il.ac.shenkar.project.costmanager.model;

import java.util.Date;

public interface IModel {

    public void addCostItem(CostItem item) throws CostManagerException;
    public void addCategory(Category category) throws CostManagerException;
    public CostItem[] getCostItems() throws CostManagerException;
    public CostItem[] getCostItems(Date date1, Date date2) throws CostManagerException;
    public Category[] getCategories() throws CostManagerException;
    public void deleteCostItem(CostItem item) throws CostManagerException;



//    public void createDB() throws CostManagerException;
//    public void closeConnection() throws CostManagerException;


}