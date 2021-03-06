/**
 * @author Halpert, Keren (313604621)
 * @author Yechezkel, Danit Noa (203964036)
 */

package il.ac.shenkar.project.costmanager.view;

import il.ac.shenkar.project.costmanager.model.Category;
import il.ac.shenkar.project.costmanager.model.CostItem;
import il.ac.shenkar.project.costmanager.model.Currency;
import il.ac.shenkar.project.costmanager.viewmodel.IViewModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Map;

/**
 * The View class is the concrete instance of the IView interface,
 * and represents the user interface of the application.
 */
public class View implements IView {
    private IViewModel vm;
    private ApplicationUI ui;

    /**
     * Sole constructor.
     * Using a thread so as not to cause backup,
     * the creation of the user interface is initiated and then run.
     */
    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui = new ApplicationUI();
                View.this.ui.start();
            }
        });
    }

    /**
     * The method to set the ViewModel object so that the View has the connection needed to function.
     *
     * @param vm the ViewModel object that is a concrete object of the IViewModel type.
     */
    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }

    /**
     * The method to display the pie chart of the cost items in the user interface.
     * Calls the user interface to execute the visualization.
     *
     * @param map the map representation of every category and its corresponding sum of expenses in it.
     */
    @Override
    public void displayPieChart(Map map) {
        ui.displayPieChart(map);
    }

    /**
     * The method to display a message in the user interface.
     * Calls the user interface to execute the visualization.
     *
     * @param text the string of the message to display in the user interface.
     */
    @Override
    public void showMessage(String text) {
        ui.showMessage(text);
    }

    /**
     * The method to display a list of cost item in the user interface.
     * Calls the user interface to execute the visualization.
     *
     * @param items the array of the cost items to display in the user interface.
     * @param type  the string representing if the list of cost items is intended to be
     *              displayed as the main table, as the table in the 'reports' frame or as a pie chart.
     */
    @Override
    public void showItems(CostItem[] items, String type) {
        ui.showItems(items, type);
    }

    /**
     * The method to display the list of categories in the user interface.
     * Calls the user interface to execute the visualization.
     *
     * @param categories the array of the categories to display in the user interface.
     */
    @Override
    public void showCategories(Category[] categories) {
        ui.showCategories(categories);
    }

    /**
     * The inner class ApplicationUI represents the UI visualization of the Cost Manager application.
     */
    public class ApplicationUI {
        // main window components
        private JFrame mainFrame;
        private JPanel addFormArea;
        private JTable itemTableArea;
        private JPanel bottomArea;
        private JPanel deleteFormArea;
        private JPanel feedbackArea;

        // 'add cost item' form components
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

        // 'add category' form components
        private JPanel addCategoryFormArea;
        private JLabel addCategoryLbl;
        private TextField addCategoryInput;
        private JButton addCategoryBtn;

        // 'delete cost item' form components
        private JLabel deleteLbl;
        private TextField itemNoInput;
        private JButton deleteCostItemBtn;

        // reports window components
        private JButton reportsBtn;
        private JFrame reportsFrame;
        private JPanel reportFormArea;
        private JPanel reportArea;
        private JTable reportTableArea;
        private JPanel reportPieArea;
        private JLabel date1Lbl;
        private TextField date1Input;
        private JLabel date2Lbl;
        private TextField date2Input;
        private JButton pieChartBtn;
        private JButton listReportBtn;

        // user feedback message
        private TextField messageField;

        /**
         * Sole constructor.
         * Initiates the UI components.
         */
        public ApplicationUI() {
            // main window components
            mainFrame = new JFrame("Cost Manager");
            addFormArea = new JPanel();
            itemTableArea = new JTable();
            bottomArea = new JPanel();
            deleteFormArea = new JPanel();
            feedbackArea = new JPanel();

            // 'add cost item' form components
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

            // 'add category' form components
            addCategoryFormArea = new JPanel();
            addCategoryLbl = new JLabel("New category name:");
            addCategoryInput = new TextField();
            addCategoryBtn = new JButton("ADD");

            // 'delete cost item' form components
            deleteLbl = new JLabel("Delete cost item (ID):");
            itemNoInput = new TextField();
            deleteCostItemBtn = new JButton("DELETE");

            // reports window components
            reportsBtn = new JButton("REPORTS");
            reportsFrame = new JFrame("Cost Manager - Reports");
            reportFormArea = new JPanel();
            reportArea = new JPanel();
            reportTableArea = new JTable();
            reportPieArea = new JPanel();
            date1Lbl = new JLabel("From (YYYY-MM-DD)");
            date1Input = new TextField();
            date2Lbl = new JLabel("To (YYYY-MM-DD)");
            date2Input = new TextField();
            pieChartBtn = new JButton("Pie Chart");
            listReportBtn = new JButton("List Report");

            // user feedback message
            messageField = new TextField();
        }

        /**
         * The method to start the application.
         * Sets up the UI components, fills them with data, creates the action
         * listeners and the functionality of the UI and displays the window.
         */
        public void start() {
            // main window settings
            mainFrame.setLayout(new BorderLayout());
            mainFrame.add("North", addFormArea);
            mainFrame.add("Center", itemTableArea);
            mainFrame.add("South", bottomArea);
            mainFrame.setSize(950, 500);

            // bottom section of main window settings
            bottomArea.setLayout(new BoxLayout(bottomArea, BoxLayout.Y_AXIS));
            bottomArea.add(addCategoryFormArea);
            bottomArea.add(deleteFormArea);
            bottomArea.add(reportsBtn);
            bottomArea.add(feedbackArea);

            // 'add cost item' form settings
            addFormArea.setLayout(new FlowLayout());
            addFormArea.add(descriptionLbl);
            addFormArea.add(descriptionInput);
            descriptionInput.setColumns(10);
            addFormArea.add(sumLbl);
            addFormArea.add(sumInput);
            sumInput.setColumns(5);
            addFormArea.add(currencyLbl);
            for (Currency currency : Currency.values()) currencyListInput.addItem(currency);
            addFormArea.add(currencyListInput);
            addFormArea.add(dateLbl);
            addFormArea.add(dateInput);
            dateInput.setColumns(10);
            addFormArea.add(categoryLbl);
            addFormArea.add(categoryListInput);
            addFormArea.add(addCostItemBtn);

            // 'add category' form settings
            addCategoryFormArea.setLayout(new FlowLayout(FlowLayout.LEFT));
            addCategoryFormArea.add(addCategoryLbl);
            addCategoryFormArea.add(addCategoryInput);
            addCategoryInput.setColumns(10);
            addCategoryFormArea.add(addCategoryBtn);

            // 'delete cost item' form settings
            deleteFormArea.setLayout(new FlowLayout(FlowLayout.LEFT));
            deleteFormArea.add(deleteLbl);
            deleteFormArea.add(itemNoInput);
            itemNoInput.setColumns(10);
            deleteFormArea.add(deleteCostItemBtn);

            // user feedback message settings
            feedbackArea.setLayout(new FlowLayout());
            feedbackArea.add(messageField);
            messageField.setColumns(50);

            // reports window settings
            reportsFrame.setLayout(new BorderLayout());
            reportsFrame.add("North", reportFormArea);
            reportsFrame.add("Center", reportArea);
            reportsFrame.setSize(600, 600);

            // reports form settings
            reportFormArea.setLayout(new GridLayout(3, 2));
            reportFormArea.add(date1Lbl);
            reportFormArea.add(date2Lbl);
            reportFormArea.add(date1Input);
            date1Input.setColumns(20);
            reportFormArea.add(date2Input);
            date2Input.setColumns(20);
            reportFormArea.add(pieChartBtn);
            reportFormArea.add(listReportBtn);

            // add window closing listeners
            mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent event) {
                    mainFrame.setVisible(false);
                    mainFrame.dispose();
                    System.exit(0);
                }
            });
            reportsFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent event) {
                    reportsFrame.setVisible(false);
                    reportPieArea.removeAll();
                    reportsFrame.dispose();
                }
            });

            // declare action listeners and add to buttons

            ActionListener addAction = event -> {
                // get the user input from the form
                String description = descriptionInput.getText();
                double sum = Double.parseDouble(sumInput.getText());
                Currency currency = (Currency) currencyListInput.getSelectedItem();
                String date = dateInput.getText();
                String category = categoryListInput.getSelectedItem().toString();

                // clear the form
                descriptionInput.setText("");
                sumInput.setText("");
                dateInput.setText("");

                // send the action request to the ViewModel
                vm.addCostItem(description, sum, currency, date, category);
            };
            // add the 'add cost item' action listener
            addCostItemBtn.addActionListener(addAction);

            ActionListener deleteAction = event -> {
                // get the user input from the form
                int id = Integer.parseInt(itemNoInput.getText());

                // clear the form
                itemNoInput.setText("");

                // send the action request to the ViewModel
                vm.deleteCostItem(id);
            };
            // add the 'delete cost item' action listener
            deleteCostItemBtn.addActionListener(deleteAction);

            ActionListener addCategoryAction = event -> {
                // get the user input from the form
                String category = addCategoryInput.getText();

                // clear the form
                addCategoryInput.setText("");

                // send the action request to the ViewModel
                vm.addCategory(category);
            };
            // add the 'add category' action listener
            addCategoryBtn.addActionListener(addCategoryAction);

            // open the 'reports' window
            ActionListener reportAction = event -> reportsFrame.setVisible(true);
            // add the 'open reports window' action listener
            reportsBtn.addActionListener(reportAction);

            ActionListener listReportAction = event -> {
                // get the user input from the form
                String date1 = date1Input.getText();
                String date2 = date2Input.getText();

                // clear the form
                date1Input.setText("");
                date2Input.setText("");

                // send the action request to the ViewModel:
                // if entered dates, get between them,
                // if no date entered, get all cost items
                if (!date1.equals("") && !date2.equals("")) {
                    Date date1Converted = Date.valueOf(date1);
                    Date date2Converted = Date.valueOf(date2);
                    vm.getCostItems(date1Converted, date2Converted, "report");
                } else {
                    vm.getCostItems("report");
                }
            };
            // add the 'get list report' action listener
            listReportBtn.addActionListener(listReportAction);

            ActionListener pieChartAction = event -> {
                // get the user input from the form
                String date1 = date1Input.getText();
                String date2 = date2Input.getText();

                // clear the form
                date1Input.setText("");
                date2Input.setText("");

                // send the action request to the ViewModel:
                // if entered dates, get between them,
                // if no date entered, get all cost items
                if (!date1.equals("") && !date2.equals("")) {
                    Date date1Converted = Date.valueOf(date1);
                    Date date2Converted = Date.valueOf(date2);
                    vm.getCostItems(date1Converted, date2Converted, "pie");
                } else {
                    vm.getCostItems("pie");
                }
            };
            // add the 'get pie chart' action listener
            pieChartBtn.addActionListener(pieChartAction);

            // fill the tables and select category field with the DB data
            vm.getCostItems("all");
            vm.getCostItems("report");
            vm.getCategories();
            mainFrame.setVisible(true);
        }

        /**
         * The method to display a message in the user feedback component.
         * Uses EDT thread so as not to cause a backup and delay in the UI.
         *
         * @param text the string of the message to display in the user interface.
         */
        public void showMessage(String text) {
            // run the functionality of the action in an EDT thread so as not to cause backup
            if (SwingUtilities.isEventDispatchThread()) {
                // display the message in the feedback text field
                messageField.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // display the message in the feedback text field
                        messageField.setText(text);
                    }
                });
            }
        }

        /**
         * The method to display a list of cost item in the table section (either in the main window
         * or int he reports window).
         * Uses EDT thread so as not to cause a backup and delay in the UI.
         *
         * @param items the array of the cost items to display in the user interface.
         * @param type  the string representing if the list of cost items is intended to be
         *              displayed as the main table, as the table in the 'reports' frame or as a pie chart.
         */
        public void showItems(CostItem[] items, String type) {
            // run the functionality of the action in an EDT thread so as not to cause backup
            if (SwingUtilities.isEventDispatchThread()) {
                // create the table to display the cost items
                String[] columnNames = {"No.", "ID", "Description", "Sum", "Currency", "Category", "Date"};
                String[][] rowData = new String[items.length][7];
                rowData[0][0] = "No.";
                rowData[0][1] = "ID";
                rowData[0][2] = "Description";
                rowData[0][3] = "Sum";
                rowData[0][4] = "Currency";
                rowData[0][5] = "Category";
                rowData[0][6] = "Date";
                for (int i = 1; i < items.length; i++) {
                    rowData[i][0] = String.valueOf(i);
                    rowData[i][1] = String.valueOf(items[i].getId());
                    rowData[i][2] = items[i].getDescription();
                    rowData[i][3] = String.valueOf(items[i].getSum());
                    rowData[i][4] = items[i].getCurrency().toString();
                    rowData[i][5] = items[i].getCategory().getName();
                    rowData[i][6] = items[i].getDate().toString();
                }
                DefaultTableModel dm = new DefaultTableModel(rowData, columnNames);

                // display the table in the main window or the reports window
                if (type.equals("all")) {
                    itemTableArea.setModel(dm);
                } else if (type.equals("report")) {
                    reportArea.setVisible(false);
                    reportArea.remove(reportPieArea);
                    reportArea.setVisible(true);
                    reportArea.add(reportTableArea);
                    reportTableArea.setModel(dm);
                }
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // create the table to display the cost items
                        String[] columnNames = {"No.", "ID", "Description", "Sum", "Currency", "Category", "Date"};
                        String[][] rowData = new String[items.length][7];
                        rowData[0][0] = "No.";
                        rowData[0][1] = "ID";
                        rowData[0][2] = "Description";
                        rowData[0][3] = "Sum";
                        rowData[0][4] = "Currency";
                        rowData[0][5] = "Category";
                        rowData[0][6] = "Date";
                        for (int i = 1; i < items.length; i++) {
                            rowData[i][0] = String.valueOf(i);
                            rowData[i][1] = String.valueOf(items[i].getId());
                            rowData[i][2] = items[i].getDescription();
                            rowData[i][3] = String.valueOf(items[i].getSum());
                            rowData[i][4] = items[i].getCurrency().toString();
                            rowData[i][5] = items[i].getCategory().getName();
                            rowData[i][6] = items[i].getDate().toString();
                        }
                        DefaultTableModel dm = new DefaultTableModel(rowData, columnNames);

                        // display the table in the main window or the reports window
                        if (type.equals("all")) {
                            itemTableArea.setModel(dm);
                        } else if (type.equals("report")) {
                            reportArea.setVisible(false);
                            reportArea.remove(reportPieArea);
                            reportArea.setVisible(true);
                            reportArea.add(reportTableArea);
                            reportTableArea.setModel(dm);
                        }
                    }
                });
            }
        }

        /**
         * The method to display the list of categories in the select box in the 'add cost item' form.
         * Uses EDT thread so as not to cause a backup and delay in the UI.
         *
         * @param categories the array of the categories to display in the user interface.
         */
        public void showCategories(Category[] categories) {
            // run the functionality of the action in an EDT thread so as not to cause backup
            if (SwingUtilities.isEventDispatchThread()) {
                // remove current dropdown list and repopulate with new list
                categoryListInput.removeAllItems();
                for (Category category : categories) {
                    categoryListInput.addItem(category.getName());
                }
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // remove current dropdown list and repopulate with new list
                        categoryListInput.removeAllItems();
                        for (Category category : categories) {
                            categoryListInput.addItem(category.getName());
                        }
                    }
                });
            }
        }

        /**
         * The method to display the pie chart of the cost items in the reports window.
         * Uses EDT thread so as not to cause a backup and delay in the UI.
         *
         * @param map the map representation of every category and its corresponding sum of expenses in it.
         */
        public void displayPieChart(Map map) {
            // run the functionality of the action in an EDT thread so as not to cause backup
            if (SwingUtilities.isEventDispatchThread()) {
                // create pie chart from map
                DefaultPieDataset dataset = new DefaultPieDataset();
                map.forEach((key, value) -> dataset.setValue(key.toString(), (Double) value));
                JFreeChart pieChart = ChartFactory.createPieChart("Cost Divisions (in ILS)", dataset,
                        true, false, false);
                pieChart.setBorderVisible(false);
                ChartPanel pie = new ChartPanel(pieChart);

                // clear existing chart or table and display the new one
                reportPieArea.removeAll();
                reportPieArea.add(pie);
                reportArea.setVisible(false);
                reportArea.remove(reportTableArea);
                reportArea.setVisible(true);
                reportArea.add(reportPieArea);

            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // create pie chart from map
                        DefaultPieDataset dataset = new DefaultPieDataset();
                        map.forEach((key, value) -> dataset.setValue(key.toString(), (Double) value));
                        JFreeChart pieChart = ChartFactory.createPieChart("Cost Divisions (in ILS)", dataset,
                                true, false, false);
                        pieChart.setBorderVisible(false);
                        ChartPanel pie = new ChartPanel(pieChart);

                        // clear existing chart or table and display the new one
                        reportPieArea.removeAll();
                        reportPieArea.add(pie);
                        reportArea.setVisible(false);
                        reportArea.remove(reportTableArea);
                        reportArea.setVisible(true);
                        reportArea.add(reportPieArea);

                    }
                });
            }
        }
    }
}