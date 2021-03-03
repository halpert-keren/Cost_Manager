package il.ac.shenkar.project.costmanager.view;

import il.ac.shenkar.project.costmanager.model.CostItem;
import il.ac.shenkar.project.costmanager.viewmodel.IViewModel;

import javax.swing.*;
import java.util.Map;

public class ApplicationUI implements IView{

    private JFrame frame;

    public ApplicationUI() {
        frame = new JFrame("Cost Manager");
    }

    public void start() {

    }

    @Override
    public void setViewModel(IViewModel vm) {

    }

    @Override
    public void displayPieChart(Map map) {

    }

    @Override
    public void showMessage(String text) {

    }

    @Override
    public void showItems(CostItem[] vec) {

    }
}