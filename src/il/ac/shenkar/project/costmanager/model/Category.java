/**
 * @author Halpert, keren (313604621)
 * @author Yechezkel, Danit Noa (203964036)
 */

package il.ac.shenkar.project.costmanager.model;

/**
 * The Category class represents a category in the Cost Manager application.
 */
public class Category {
    private String name;

    /**
     * Sole constructor.
     * Creates an instance of a category object.
     *
     * @param name the name of the new category.
     * @throws CostManagerException if could not set the category name.
     */
    public Category(String name) throws CostManagerException {
        try {
            setName(name);
        } catch (CostManagerException e) {
            throw new CostManagerException(e.getMessage());
        }
    }

    /**
     * The method to set the name of the category.
     * Validate that the name is not an empty string.
     *
     * @param name the name of the new category.
     * @throws CostManagerException if the name is an empty string.
     */
    public void setName(String name) throws CostManagerException {
        if (!name.equals(""))
            this.name = name;
        else
            throw new CostManagerException("Can't create a cost item without a description.");
    }

    /**
     * The method to get the name of the category.
     *
     * @return a string of the name of the category.
     */
    public String getName() {
        return name;
    }
}
