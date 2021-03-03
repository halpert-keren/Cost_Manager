package il.ac.shenkar.project.costmanager.model;

public interface IModel {
    public void addCostItem(CostItem item) throws CostManagerException;
    public CostItem[] getCostItems() throws CostManagerException;
    public void deleteCostItem(CostItem item) throws CostManagerException;

}