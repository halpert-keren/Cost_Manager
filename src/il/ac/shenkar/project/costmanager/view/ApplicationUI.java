package il.ac.shenkar.project.costmanager.view;

import il.ac.shenkar.project.costmanager.model.Category;
import il.ac.shenkar.project.costmanager.model.CostItem;
import il.ac.shenkar.project.costmanager.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ApplicationUI implements IView{

    private JFrame frame;
    private TextField textFieldMessage;
    private TextArea textAreaItems;

    public ApplicationUI() {
        frame = new JFrame("Cost Manager");
        textFieldMessage = new TextField();
        textAreaItems = new TextArea();
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
        if (SwingUtilities.isEventDispatchThread()) {
            textFieldMessage.setText(text);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    textFieldMessage.setText(text);
                }
            });
        }
    }

    @Override
    public void showItems(CostItem[] items) {
        StringBuilder sb = new StringBuilder();
        for (CostItem item: items) {
            sb.append(items.toString());
            sb.append("\n");
        }
        String text = sb.toString();

        if (SwingUtilities.isEventDispatchThread()) {
            textAreaItems.setText(text);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    textAreaItems.setText(text);
                }
            });
        }
    }

    @Override
    public void showCategories(Category[] categories) {

    }
}