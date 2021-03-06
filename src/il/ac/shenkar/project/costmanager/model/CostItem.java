/**
 * @author Halpert, Keren (313604621)
 * @author Yechezkel, Danit Noa (203964036)
 */

package il.ac.shenkar.project.costmanager.model;

import java.sql.Date;

/**
 * The CostItem class represents a cost item in the Cost Manager application.
 */
public class CostItem {
    private int id;
    private String description;
    private double sum;
    private Currency currency;
    private Date date;
    private Category category;

    /**
     * Sole constructor.
     * Creates an instance of a cost item object.
     *
     * @param id          unique id for each cost item, comes from the DB.
     * @param description indicates the description of the cost item.
     * @param sum         indicates the expense/price of the cost item.
     * @param currency    indicates the currency of the cost item.
     * @param date        indicates the date in which the expense was made.
     * @param category    indicates category that the cost item belongs to.
     */
    public CostItem(int id, String description, double sum, Currency currency, String date, Category category)
            throws CostManagerException {
        try {
            setId(id);
            setDescription(description);
            setSum(sum);
            setCurrency(currency);
            setDate(date);
            setCategory(category);
        } catch (CostManagerException e) {
            throw new CostManagerException(e.getMessage());
        }
    }

    /**
     * The method to set the id of the cost item.
     *
     * @param id the id of the new cost item.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The method to set the description of the cost item.
     * Validate that the description is not an empty string.
     *
     * @param description the description of the new cost item.
     * @throws CostManagerException if the description is an empty string.
     */
    public void setDescription(String description) throws CostManagerException {
        if (!description.equals(""))
            this.description = description;
        else
            throw new CostManagerException("Can't create a cost item without a description.");
    }

    /**
     * The method to set the sum of the cost item.
     * Validate that the sum is not a negative number or zero.
     *
     * @param sum the sum of the new cost item.
     * @throws CostManagerException if the sum is not a positive number.
     */
    public void setSum(double sum) throws CostManagerException {
        if (sum > 0)
            this.sum = sum;
        else
            throw new CostManagerException("Can't create a cost item without an positive expense.");
    }

    /**
     * The method to set the currency of the cost item.
     *
     * @param currency the currency of the new cost item.
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * The method to set the date of the cost item.
     * Validate that the date is valid and converts correctly to a java.sql.Date type.
     *
     * @param date the string representation of the date of the new cost item.
     * @throws CostManagerException if the date is not valid.
     */
    public void setDate(String date) throws CostManagerException {
        try {
            this.date = Date.valueOf(date);
        } catch (Exception e) {
            throw new CostManagerException("Date of cost item is invalid.");
        }
    }

    /**
     * The method to set the category of the cost item.
     *
     * @param category the category of the new cost item.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * The method to get the id of the cost item.
     *
     * @return an int of the id of the cost item.
     */
    public int getId() {
        return id;
    }

    /**
     * The method to get the description of the cost item.
     *
     * @return a string of the description of the cost item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * The method to get the sum of the cost item.
     *
     * @return a double of the sum of the cost item.
     */
    public double getSum() {
        return sum;
    }

    /**
     * The method to get the currency of the cost item.
     *
     * @return a Currency object of the currency of the cost item.
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * The method to get the date of the cost item.
     *
     * @return a java.sql.Date object of the date of the cost item.
     */
    public Date getDate() {
        return date;
    }

    /**
     * The method to get the category of the cost item.
     *
     * @return a Category object of the category of the cost item.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * The method to get the sum of the cost item converted to ILS currency.
     *
     * @return a double of the converted sum of the cost item.
     */
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