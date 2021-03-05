/**
 * @author Halpert, keren (313604621)
 * @author Yechezkel, Danit Noa (203964036)
 */

package il.ac.shenkar.project.costmanager;

import il.ac.shenkar.project.costmanager.model.CostManagerException;
import il.ac.shenkar.project.costmanager.model.DerbyDBModel;
import il.ac.shenkar.project.costmanager.model.IModel;
import il.ac.shenkar.project.costmanager.view.IView;
import il.ac.shenkar.project.costmanager.view.View;
import il.ac.shenkar.project.costmanager.viewmodel.IViewModel;
import il.ac.shenkar.project.costmanager.viewmodel.ViewModel;

/**
 * The CostManager main class.
 */
public class CostManager {
    /**
     * Cost Manager main function. Runs the application.
     */
    public static void main(String[] args) {
        try {
            //creating the application components
            IModel model = new DerbyDBModel();
            IView view = new View();
            IViewModel vm = new ViewModel();

            //connecting the components with each other
            view.setViewModel(vm);
            vm.setModel(model);
            vm.setView(view);
        } catch (CostManagerException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}