package il.ac.shenkar.project.costmanager.view;

import il.ac.shenkar.project.costmanager.model.Category;
import il.ac.shenkar.project.costmanager.model.CostItem;
import il.ac.shenkar.project.costmanager.model.Currency;
import il.ac.shenkar.project.costmanager.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Map;

public class ApplicationUI extends View{
    private IViewModel vm;

    private JFrame mainFrame;
    private JPanel addFormArea;
    private JTable itemTableArea;
    private JPanel bottomArea;
    private JPanel deleteFormArea;
    private JPanel feedbackArea;

    private JLabel descriptionLbl;
    private TextField descriptionInput;
    private JLabel sumLbl;
    private TextField sumInput;
    private JLabel currencyLbl;
    private JComboBox currencyListInput;
    private JLabel dateLbl;
    private TextField dateInput;
    private JLabel categoryLbl;
    private JComboBox categoryListInput;
    private JButton addCostItemBtn;

    private JLabel deleteLbl;
    private TextField itemNoInput;
    private JButton deleteCostItemBtn;
    private JButton reportsBtn;
    private TextField messageField;

    private JFrame reportsFrame;
    private JPanel reportFormArea;
    private JPanel reportArea;
    private JLabel date1Lbl;
    private TextField date1Input;
    private JLabel date2Lbl;
    private TextField date2Input;
    private JButton pieChartBtn;
    private JButton listReportBtn;


    public ApplicationUI() {
        mainFrame = new JFrame("Cost Manager");
        addFormArea = new JPanel();
        itemTableArea = new JTable();
        bottomArea = new JPanel();
        deleteFormArea = new JPanel();
        feedbackArea = new JPanel();

        mainFrame.setLayout(new BorderLayout());
        mainFrame.add("North", addFormArea);
        mainFrame.add("Center", itemTableArea);
        mainFrame.add("South", bottomArea);

        bottomArea.setLayout(new BorderLayout());
        bottomArea.add("North", deleteFormArea);
        bottomArea.add("Center", reportsBtn);
        bottomArea.add("South", feedbackArea);

        addFormArea.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        descriptionLbl = new JLabel("Description");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        addFormArea.add(descriptionLbl, gbc);

        descriptionInput = new TextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        addFormArea.add(descriptionInput, gbc);

        sumLbl = new JLabel("Sum");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        addFormArea.add(sumLbl, gbc);

        sumInput = new TextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        addFormArea.add(sumInput, gbc);

        currencyLbl = new JLabel("Currency");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        addFormArea.add(currencyLbl, gbc);

        currencyListInput = new JComboBox();
        for (Currency currency: Currency.values()){
            currencyListInput.addItem(currency);
        }
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        addFormArea.add(currencyListInput, gbc);

        dateLbl = new JLabel("Date (YYYY-MM-DD)");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        addFormArea.add(dateLbl, gbc);

        dateInput = new TextField();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        addFormArea.add(dateInput, gbc);

        categoryLbl = new JLabel("Category");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        addFormArea.add(categoryLbl, gbc);

        categoryListInput = new JComboBox();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        addFormArea.add(categoryListInput, gbc);

        addCostItemBtn = new JButton("ADD");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        addFormArea.add(addCostItemBtn, gbc);

        deleteFormArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        deleteLbl = new JLabel("Delete cost item (No.):");
        deleteFormArea.add(deleteLbl);
        itemNoInput  = new TextField();
        deleteFormArea.add(itemNoInput);
        deleteCostItemBtn = new JButton("DELETE");
        deleteFormArea.add(deleteCostItemBtn);

        feedbackArea.setLayout(new FlowLayout());
        messageField = new TextField();
        feedbackArea.add(messageField);

        reportsFrame = new JFrame("Cost Manager - Reports");
        reportsFrame.setLayout(new BorderLayout());
        reportFormArea = new JPanel();
        reportsFrame.add("North", reportFormArea);
        reportArea = new JPanel();
        reportsFrame.add("Center", reportArea);

        reportFormArea.setLayout(new GridLayout(3, 2));
        date1Lbl = new JLabel("From (YYYY-MM-DD)");
        reportFormArea.add(date1Lbl);
        date2Lbl = new JLabel("To (YYYY-MM-DD)");
        reportFormArea.add(date2Lbl);
        date1Input = new TextField();
        reportFormArea.add(date1Input);
        date2Input = new TextField();
        reportFormArea.add(date2Input);
        pieChartBtn = new JButton();
        reportFormArea.add(pieChartBtn);
        listReportBtn = new JButton();
        reportFormArea.add(listReportBtn);
    }

    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }

    public void start() {
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                mainFrame.setVisible(false);
                mainFrame.dispose();
                reportsFrame.dispose();
                System.exit(0);
            }
        });

        reportsFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                reportsFrame.setVisible(false);
            }
        });

        ActionListener addAction = event -> {
            String description = descriptionInput.getText();
            double sum = Double.parseDouble(sumInput.getText());
            Currency currency = (Currency) currencyListInput.getSelectedItem();
            Date date = Date.valueOf(dateInput.getText());
            Category category = (Category) categoryListInput.getSelectedItem();
            CostItem costItem = new CostItem(description, sum, currency, date, category);
            vm.addCostItem(costItem);
        };
        addCostItemBtn.addActionListener(addAction);

        ActionListener deleteAction = event -> {
            int id = Integer.parseInt(itemNoInput.getText());
            vm.deleteCostItem(id);
        };
        addCostItemBtn.addActionListener(deleteAction);

        ActionListener reportAction = event -> {
            reportsFrame.setVisible(true);
        };
        addCostItemBtn.addActionListener(reportAction);

        ActionListener listReportAction = event -> {
            Date date1 = Date.valueOf(date1Input.getText());
            Date date2 = Date.valueOf(date2Input.getText());
            vm.getCostItems(date1, date2, "list");
        };
        addCostItemBtn.addActionListener(listReportAction);

        ActionListener pieChartAction = event -> {
            Date date1 = Date.valueOf(date1Input.getText());
            Date date2 = Date.valueOf(date2Input.getText());
            vm.getCostItems(date1, date2, "pie");
        };
        addCostItemBtn.addActionListener(pieChartAction);

        mainFrame.setVisible(true);
    }

    @Override
    public void showMessage(String text) {
        if (SwingUtilities.isEventDispatchThread()) {
            messageField.setText(text);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    messageField.setText(text);
                }
            });
        }
    }

    @Override
    public void showItems(CostItem[] items) {
        String[] columnNames = {"No.", "ID", "Description", "Sum", "Currency", "Category", "Date"};
        String[][] rowData = new String[items.length][7];
        for (int i = 0; i < items.length; i++) {
            rowData[i][0] = String.valueOf(i);
            rowData[i][1] = String.valueOf(items[i].getId());
            rowData[i][2] = items[i].getDescription();
            rowData[i][3] = String.valueOf(items[i].getSum());
            rowData[i][4] = items[i].getCurrency().toString();
            rowData[i][5] = items[i].getCategory().getName();
            rowData[i][6] = items[i].getDate().toString();
        }

        if (SwingUtilities.isEventDispatchThread()) {
            itemTableArea = new JTable(rowData, columnNames);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    itemTableArea = new JTable(rowData, columnNames);
                }
            });
        }
    }

    @Override
    public void showCategories(Category[] categories) {
        if (SwingUtilities.isEventDispatchThread()) {
            for (Category category: categories){
                categoryListInput.addItem(category);
            }
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    for (Category category: categories){
                        categoryListInput.addItem(category);
                    }
                }
            });
        }
    }

    @Override
    public void displayPieChart(Map map) {

    }
}