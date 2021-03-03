package il.ac.shenkar.project.costmanager.view;

import il.ac.shenkar.project.costmanager.model.CostItem;
import il.ac.shenkar.project.costmanager.viewmodel.IViewModel;

import java.util.*;

public interface IView {
    public void setViewModel(IViewModel vm);
    public void displayPieChart(Map map);
    public void showMessage(String text);
    public void showItems(CostItem[] vec);

}