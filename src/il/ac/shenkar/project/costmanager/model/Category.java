/**
 * Class Category contains in Model.
 * Class Category contains 5 methods.
 * The goal of this class do gets and sets on Category of database
 * @param name is an object of type category
 * @param id is an object of type unique category
 */

package il.ac.shenkar.project.costmanager.model;

public class Category {
    private String name;
    private int id;
    /*
     * initialization db in method Category
     */
    public Category(String name) {
        setName(name);
        setId();
    }
    /*
     * function setName doing updates to name category
     */
    public void setName(String name) {
        this.name = name;
    }
    /*
     * function setId doing updates id specific
     */
    public void setId() {
        this.id = id;
    }
    /*
     * function getName get data from table Category by name
     */
    public String getName() {
        return name;
    }
    /*
     * function getId get data from table Category by id specific
     */
    public int getId() {
        return id;
    }
}
