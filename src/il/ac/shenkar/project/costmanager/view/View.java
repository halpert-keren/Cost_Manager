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

public class View implements IView {
    private IViewModel vm;
    private ApplicationUI ui;

    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui = new ApplicationUI();
                View.this.ui.setViewModel(vm);
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
        ui.displayPieChart(map);
    }

    @Override
    public void showMessage(String text) {
        ui.showMessage(text);
    }

    @Override
    public void showItems(CostItem[] items, String type) {
        ui.showItems(items, type);
    }

    @Override
    public void showCategories(Category[] categories) {
        ui.showCategories(categories);
    }

    public class ApplicationUI {
        private IViewModel vm;

        private JFrame mainFrame;
        private JPanel addFormArea;
        private JTable itemTableArea;
        private JPanel bottomArea;
        private JPanel addCategoryFormArea;
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

        private JLabel addCategoryLbl;
        private TextField addCategoryInput;
        private JButton addCategoryBtn;
        private JLabel deleteLbl;
        private TextField itemNoInput;
        private JButton deleteCostItemBtn;
        private JButton reportsBtn;
        private TextField messageField;

        private JFrame reportsFrame;
        private JPanel reportFormArea;
        private JPanel reportArea;
        private JTable reportTableArea;
        private JLabel date1Lbl;
        private TextField date1Input;
        private JLabel date2Lbl;
        private TextField date2Input;
        private JButton pieChartBtn;
        private JButton listReportBtn;

        public ApplicationUI() {
            mainFrame = new JFrame("Cost Manager");
            addFormArea = new JPanel();
            bottomArea = new JPanel();
            deleteFormArea = new JPanel();
            feedbackArea = new JPanel();

            itemTableArea = new JTable();
            reportTableArea = new JTable();
            reportsBtn = new JButton("REPORTS");
            descriptionLbl = new JLabel("Description");
            descriptionInput = new TextField();
            sumLbl = new JLabel("Sum");
            sumInput = new TextField();
            currencyLbl = new JLabel("Currency");
            currencyListInput = new JComboBox();
            dateLbl = new JLabel("Date (YYYY-MM-DD)");
            dateInput = new TextField();
            categoryLbl = new JLabel("Category");
            categoryListInput = new JComboBox();
            addCostItemBtn = new JButton("ADD");

            addCategoryFormArea = new JPanel();
            addCategoryLbl = new JLabel("New category name:");
            addCategoryInput = new TextField();
            addCategoryBtn = new JButton("ADD");

            deleteLbl = new JLabel("Delete cost item (ID):");
            itemNoInput = new TextField();
            deleteCostItemBtn = new JButton("DELETE");

            messageField = new TextField();

            reportsFrame = new JFrame("Cost Manager - Reports");
            reportFormArea = new JPanel();
            reportArea = new JPanel();
            date1Lbl = new JLabel("From (YYYY-MM-DD)");
            date2Lbl = new JLabel("To (YYYY-MM-DD)");
            date1Input = new TextField();
            date2Input = new TextField();
            pieChartBtn = new JButton();
            listReportBtn = new JButton();
        }

        public void setViewModel(IViewModel vm) {
            this.vm = vm;
        }

        public void start() {
            mainFrame.setLayout(new BorderLayout());
            mainFrame.add("North", addFormArea);
            mainFrame.add("Center", itemTableArea);
            mainFrame.add("South", bottomArea);

            bottomArea.setLayout(new BoxLayout(bottomArea, BoxLayout.Y_AXIS));
            bottomArea.add(addCategoryFormArea);
            bottomArea.add(deleteFormArea);

            bottomArea.add(reportsBtn);
            bottomArea.add(feedbackArea);

            addFormArea.setLayout(new FlowLayout());
            addFormArea.add(descriptionLbl);
            addFormArea.add(descriptionInput);
            addFormArea.add(sumLbl);
            addFormArea.add(sumInput);
            addFormArea.add(currencyLbl);
            for (Currency currency : Currency.values()) currencyListInput.addItem(currency);
            addFormArea.add(currencyListInput);
            addFormArea.add(dateLbl);
            addFormArea.add(dateInput);
            addFormArea.add(categoryLbl);
            addFormArea.add(categoryListInput);
            addFormArea.add(addCostItemBtn);

            addCategoryFormArea.setLayout(new FlowLayout(FlowLayout.LEFT));
            addCategoryFormArea.add(addCategoryLbl);
            addCategoryFormArea.add(addCategoryInput);
            addCategoryFormArea.add(addCategoryBtn);

            deleteFormArea.setLayout(new FlowLayout(FlowLayout.LEFT));
            deleteFormArea.add(deleteLbl);
            deleteFormArea.add(itemNoInput);
            deleteFormArea.add(deleteCostItemBtn);

            feedbackArea.setLayout(new FlowLayout());
            feedbackArea.add(messageField);

            reportsFrame.setLayout(new BorderLayout());
            reportsFrame.add("North", reportFormArea);
            reportsFrame.add("Center", reportArea);

            reportFormArea.setLayout(new GridLayout(3, 2));
            reportFormArea.add(date1Lbl);
            reportFormArea.add(date2Lbl);
            reportFormArea.add(date1Input);
            reportFormArea.add(date2Input);
            reportFormArea.add(pieChartBtn);
            reportFormArea.add(listReportBtn);


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

            ActionListener addCategoryAction = event -> {
                String categoryName = (String) categoryListInput.getSelectedItem();
                Category category = new Category(categoryName);
                vm.addCategory(category);
            };
            addCostItemBtn.addActionListener(addCategoryAction);

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

            vm.getCostItems("all");
            vm.getCostItems("report");
            vm.getCategories();
            mainFrame.setVisible(true);
        }

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

        public void showItems(CostItem[] items, String type) {
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
                if (type.equals("all"))
                    itemTableArea = new JTable(rowData, columnNames);
                else if (type.equals("report")) {
                    reportTableArea = new JTable(rowData, columnNames);
                    reportArea.add(reportTableArea);
                }
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (type.equals("all"))
                            itemTableArea = new JTable(rowData, columnNames);
                        else if (type.equals("report")) {
                            reportTableArea = new JTable(rowData, columnNames);
                            reportArea.add(reportTableArea);
                        }
                    }
                });
            }
        }

        public void showCategories(Category[] categories) {
            if (SwingUtilities.isEventDispatchThread()) {
                for (Category category : categories) {
                    categoryListInput.addItem(category);
                }
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        for (Category category : categories) {
                            categoryListInput.addItem(category);
                        }
                    }
                });
            }
        }

        public void displayPieChart(Map map) {

            if (SwingUtilities.isEventDispatchThread()) {

            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }
    }
}