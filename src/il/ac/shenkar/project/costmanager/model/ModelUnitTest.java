package il.ac.shenkar.project.costmanager.model;

import java.sql.Date;
import java.util.Arrays;

public class ModelUnitTest {
    public static void main(String[] args) {
        try {
            IModel model = new DerbyDBModel();

            Category category = new Category("Clothes");
//            model.addCategory(category);

//            Category[] categories = model.getCategories();
//            System.out.println(categories[1].getName());
            CostItem costItem = new CostItem(2, "bb", 3.2, Currency.EURO, Date.valueOf("2021-03-03"), category);
            model.addCostItem(costItem);

            CostItem[] costItems = model.getCostItems();
            System.out.println(costItems[1].getDescription());


        } catch (CostManagerException e) {
            e.printStackTrace();
        }
    }
}