/**
 *  Class CostItem includes variables to describe the cost.
 * Class CostItem includes method on the variables like set and get.
 * @param description indicates the description of the cost .
 * @param sum Indicates the cost of cost
 * @param currency what currency it was cost
 * @param id unique id for each cost
 * @param date what date was the cost made
 * @param category which category does the cost belong
 */

package il.ac.shenkar.project.costmanager.model;

import java.sql.Date;

public class CostItem {
    private String description;
    private double sum;
    private Currency currency;
    private int id;
    private Date date;
    private Category category;

    /**
     * constructor for creating new cost item
     * initialization variables:description, sum, currency, id, date, Category.
     */
    public CostItem(int id, String description, double sum, Currency currency, Date date, Category category) {
        setDescription(description);
        setSum(sum);
        setCurrency(currency);
        setId(id);
        setDate(date);
        setCategory(category);
    }
    /*
     * function setDescription updates description of cost item
     */
    public void setDescription(String description) { this.description = description; }
    /*
     * function setSum updates sum of cost item
     */
    public void setSum(double sum) { this.sum = sum; }
    /*
     * function setCurrency updates currency of cost item
     */
    public void setCurrency(Currency currency) { this.currency = currency; }
    /*
     * function setId updates unique id of cost item
     */
    public void setId(int id) {
        this.id = id;
    }
    /*
     * function setDate updates date of cost item
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /*
     * function setCategory updates category of cost item
     */
    public void setCategory(Category category) {
        this.category = category;
    }
    /*
     * function getDescription get description of cost item
     */
    public String getDescription() { return description; }
    /*
     * function getSum get sum of cost item
     */
    public double getSum() { return sum; }
    /*
     * function getCurrency get currency of cost item
     */
    public Currency getCurrency() { return currency; }
    /*
     * function getId get unique id of cost item
     */
    public int getId() {
        return id;
    }
    /*
     * function getDate get date of cost item
     */
    public Date getDate() {
        return date;
    }
    /*
     * function getCategory get category of cost item
     */
    public Category getCategory() {
        return category;
    }

}