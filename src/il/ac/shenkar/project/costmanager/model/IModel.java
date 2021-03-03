package il.ac.shenkar.project.costmanager.model;

public interface IModel {

    public void addCostItem(CostItem item) throws CostManagerException;
    public void addCategory(Category category) throws CostManagerException;
    public CostItem[] getCostItems() throws CostManagerException;
    public Category[] getCategories() throws CostManagerException;
    public void deleteCostItem(CostItem item) throws CostManagerException;



//    public void createDB() throws CostManagerException;
//    public void closeConnection() throws CostManagerException;


}