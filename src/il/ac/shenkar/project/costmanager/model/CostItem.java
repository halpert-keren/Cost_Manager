package il.ac.shenkar.project.costmanager.model;

import java.sql.Date;

public class CostItem {
    private String description;
    private double sum;
    private Currency currency;
    private int id;
    private Date date;
    private Category category;


    public CostItem(String description, double sum, Currency currency, Date date, Category category) {
        setDescription(description);
        setSum(sum);
        setCurrency(currency);
        setId();
        setDate(date);
        setCategory(category);
    }

    public void setDescription(String description) { this.description = description; }
    public void setSum(double sum) { this.sum = sum; }
    public void setCurrency(Currency currency) { this.currency = currency; }
    public void setId() {
        this.id = id;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() { return description; }
    public double getSum() { return sum; }
    public Currency getCurrency() { return currency; }
    public int getId() {
        return id;
    }
    public Date getDate() {
        return date;
    }
    public Category getCategory() {
        return category;
    }

}