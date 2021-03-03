package il.ac.shenkar.project.costmanager;

import il.ac.shenkar.project.costmanager.model.*;
import il.ac.shenkar.project.costmanager.view.*;
import il.ac.shenkar.project.costmanager.viewmodel.*;

public class CostManager {
    public static void main(String[] args) {

        IModel model = new DerbyDBModel();
        IView view = new View();
        IViewModel vm = new ViewModel();

        view.setViewModel(vm);
        vm.setModel(model);
        vm.setView(view);
    }
}