/**
 * @author Halpert, Keren (313604621)
 * @author Yechezkel, Danit Noa (203964036)
 */

package il.ac.shenkar.project.costmanager.model;

public class ModelUnitTest {
    public static void main(String[] args) {
        try {
            IModel model = new DerbyDBModel();

            Category c = new Category("Clothes");
//            model.addCategory(c);

//            Category[] categories = model.getCategories();
//            System.out.println(categories[1].getName());
            CostItem ci = new CostItem(2, "test", 3.2, Currency.EURO, "2021-03-03", c);
            model.addCostItem(ci);

            CostItem[] costItems = model.getCostItems();
            System.out.println(costItems[1].getDescription());


        } catch (CostManagerException e) {
            e.printStackTrace();
        }
    }
}