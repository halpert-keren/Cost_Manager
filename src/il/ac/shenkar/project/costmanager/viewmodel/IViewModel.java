package il.ac.shenkar.project.costmanager.viewmodel;

import il.ac.shenkar.project.costmanager.model.CostItem;
import il.ac.shenkar.project.costmanager.model.IModel;
import il.ac.shenkar.project.costmanager.view.IView;

public interface IViewModel {
    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(CostItem item);

}