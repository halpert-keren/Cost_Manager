package il.ac.shenkar.project.costmanager.model;

public class CostItem {
    private String description;
    private double sum;
    private Currency currency;
    private int id;

    public void setDescription(String description) { this.description = description; }

    public void setSum(double sum) { this.sum = sum; }

    public String getDescription() { return description; }

    public double getSum() { return sum; }

    public Currency getCurrency() { return currency; }

    public void setCurrency(Currency currency) { this.currency = currency; }

    public CostItem(String description, double sum, Currency currency) {
        this.id = 0; // unique id
        setDescription(description);
        setSum(sum);
        setCurrency(currency);
    }
//    public void addCostItem(CostItem item) throws CostManagerException;
//    public void deleteCostItem(CostItem item) throws CostManagerException;

}