package il.ac.shenkar.project.costmanager.model;

public class ModelUnitTest {
    public static void main(String[] args) {
        try {
            IModel model = new DerbyDBModel();

            Category category = new Category("Clothes");
//            model.addCategory(category);

//            Category[] categories = model.getCategories();
//            System.out.println(categories[1].getName());
            CostItem costItem = new CostItem(2, "test", 3.2, Currency.EURO, "2021-03-03", category);
            model.addCostItem(costItem);

            CostItem[] costItems = model.getCostItems();
            System.out.println(costItems[1].getDescription());


        } catch (CostManagerException e) {
            e.printStackTrace();
        }
    }
}