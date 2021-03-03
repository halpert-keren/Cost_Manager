package il.ac.shenkar.project.costmanager.view;

import il.ac.shenkar.project.costmanager.model.CostItem;
import il.ac.shenkar.project.costmanager.viewmodel.IViewModel;

import javax.swing.*;
import java.util.Map;

public class View implements IView{
    private IViewModel vm;
    private ApplicationUI ui;

    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui = new ApplicationUI();
                View.this.ui.start();
            }
        });
    }

    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }

    @Override
    public void displayPieChart(Map map) {

    }

    @Override
    public void showMessage(String text) {
        ui.showMessage(text);
    }

    @Override
    public void showItems(CostItem[] vec) {
        ui.showItems(vec);
    }

}