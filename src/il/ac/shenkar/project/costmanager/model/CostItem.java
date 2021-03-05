/**
 * Class CostItem includes variables to describe the cost.
 * Class CostItem includes method on the variables like set and get.
 *
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
    public CostItem(int id, String description, double sum, Currency currency, String date, Category category) throws CostManagerException {
        try {
            setDescription(description);
            setSum(sum);
            setCurrency(currency);
            setId(id);
            setDate(date);
            setCategory(category);
        } catch (CostManagerException e) {
            throw new CostManagerException(e.getMessage());
        }
    }

    /*
     * function setDescription updates description of cost item
     */
    public void setDescription(String description) throws CostManagerException {
        if (!description.equals(""))
            this.description = description;
        else
            throw new CostManagerException("Can't create a cost item without a description.");
    }

    /*
     * function setSum updates sum of cost item
     */
    public void setSum(double sum) throws CostManagerException {
        if (sum > 0)
            this.sum = sum;
        else
            throw new CostManagerException("Can't create a cost item without an positive expense.");
    }

    /*
     * function setCurrency updates currency of cost item
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /*
     * function setId updates unique id of cost item
     */
    public void setId(int id) {
        this.id = id;
    }

    /*
     * function setDate updates date of cost item
     */
    public void setDate(String date) throws CostManagerException {
        try {
            this.date = Date.valueOf(date);
        } catch (Exception e) {
            throw new CostManagerException("Date of cost item is invalid.");
        }
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
    public String getDescription() {
        return description;
    }

    /*
     * function getSum get sum of cost item
     */
    public double getSum() {
        return sum;
    }

    /*
     * function getCurrency get currency of cost item
     */
    public Currency getCurrency() {
        return currency;
    }

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

    public double convertCurrencyToILS() {
        double converted = 0.0;
        switch (this.currency) {
            case GBP -> converted = this.sum * 4.58;
            case EURO -> converted = this.sum * 3.95;
            case USD -> converted = this.sum * 3.31;
            case ILS -> converted = this.sum;
        }
        return converted;
    }
}