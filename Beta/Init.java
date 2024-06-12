import java.awt.Font;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

class Budget {
    private double sumBudget;
    private double sumUsed;

    // No-args Constructor
    public Budget() {
        this.sumBudget = 0;
        this.sumUsed = 0;
    }

    // Budget method
    public void setBudget(double sumBudget) {
        this.sumBudget = sumBudget;
    }

    public double getBudget() {
        return sumBudget;
    }

    // Amount method
    public void setUsed(double sumUsed) {
        this.sumUsed = sumUsed;
    }

    public double getUsed() {
        return sumUsed;
    }

    public void addExpense(double amount) {
        if (amount > 0 && (sumUsed + amount) <= sumBudget)
            sumUsed += amount;
    }

    public double getRemainingBudget() {
        return sumBudget - sumUsed;
    }

    public void displayBudget() {
        JOptionPane.showMessageDialog(null, "Total Budget: RM" + sumBudget + "\nSpent Amount: RM" +
                sumUsed + "\nRemaining Budget: RM" + getRemainingBudget());
    }
}

class Activities extends Budget {
    private String activityName;
    private double activityCost;

    // No-args Constructor
    public Activities() {
        super();
    }

    // Name method
    public void setName(String activityName) {
        this.activityName = activityName;
    }

    public String getName() {
        return activityName;
    }

    // Cost method
    public void setCost(double activityCost) {
        this.activityCost = activityCost;
    }

    public double getCost() {
        return activityCost;
    }

    public void setActivityDetails(String activityName, double activityCost) {
        this.activityName = activityName;
        this.activityCost = activityCost;
    }

    public void addActivityExpense() {
        addExpense(activityCost);
        JOptionPane.showMessageDialog(null, "Added : " + activityName + " with cost: RM" + activityCost);
    }

    public void displayBudget() {
        super.displayBudget();
        JOptionPane.showMessageDialog(null, "Activity Name: " + activityName + "\nActivity Cost: RM" + activityCost);
    }
}

class Transportation extends Budget {
    private String transportType;
    private double transportCost;

    public Transportation() {
        super();
    }

    public void setTransportDetails(String transportType, double transportCost) {
        this.transportType = transportType;
        this.transportCost = transportCost;
    }

    public void addTransportExpense() {
        addExpense(transportCost);
        JOptionPane.showMessageDialog(null, "Added transport: " + transportType + " with cost: " + transportCost);
    }

    public void displayBudget() {
        super.displayBudget();
        JOptionPane.showMessageDialog(null, "Transport Type: " + transportType + "\nTransport Cost: " + transportCost);
    }
}

public class Init {
    public static void main(String[] args) {
        Vector<Budget> tripBudgets = new Vector<>();

        JFrame frame = new JFrame("Camping Trips Planner!");
        frame.setSize(450, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        JLabel header = new JLabel("Plan your trips!");
        header.setBounds(30, 1, 200, 100);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(header);

        JButton addTripButton = new JButton("Add New Trip");
        addTripButton.setBounds(50, 150, 150, 30);
        frame.add(addTripButton);

        JButton showTripButton = new JButton("Show Trips");
        showTripButton.setBounds(230, 150, 150, 30);
        frame.add(showTripButton);

        addTripButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.setText("Add New Trip");
                frame.remove(addTripButton);
                frame.remove(showTripButton);

                JLabel labelBudget = new JLabel("Total Budget");
                labelBudget.setBounds(30, 50, 300, 100);
                frame.add(labelBudget);

                JTextField budgetField = new JTextField();
                budgetField.setBounds(30, 110, 150, 30);
                frame.add(budgetField);

                JLabel labelActivityName = new JLabel("Activity Name");
                labelActivityName.setBounds(30, 120, 300, 100);
                frame.add(labelActivityName);

                JTextField activityNameField = new JTextField();
                activityNameField.setBounds(30, 180, 150, 30);
                frame.add(activityNameField);

                JLabel labelActivityCost = new JLabel("Activity Cost");
                labelActivityCost.setBounds(30, 190, 300, 100);
                frame.add(labelActivityCost);

                JTextField activityCostField = new JTextField();
                activityCostField.setBounds(30, 250, 150, 30);
                frame.add(activityCostField);

                JLabel labelTransportType = new JLabel("Transport Type");
                labelTransportType.setBounds(230, 50, 300, 100);
                frame.add(labelTransportType);

                String[] transportOptions = { "Car", "Bus", "Train", "Miscellaneous" };
                JComboBox<String> transportTypeComboBox = new JComboBox<>(transportOptions);
                transportTypeComboBox.setBounds(230, 110, 150, 30);
                frame.add(transportTypeComboBox);

                JLabel labelTransportCost = new JLabel("Transport Cost");
                labelTransportCost.setBounds(230, 120, 300, 100);
                frame.add(labelTransportCost);

                JTextField transportCostField = new JTextField();
                transportCostField.setBounds(230, 180, 150, 30);
                frame.add(transportCostField);

                JButton saveButton = new JButton("Save Trip");
                saveButton.setBounds(300, 340, 100, 30);
                frame.add(saveButton);

                saveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        double budget = Double.parseDouble(budgetField.getText());
                        String activityName = activityNameField.getText();
                        double activityCost = Double.parseDouble(activityCostField.getText());

                        Budget tripBudget = new Budget();
                        tripBudget.setBudget(budget);
                        Activities activities = new Activities();
                        activities.setActivityDetails(activityName, activityCost);
                        activities.addActivityExpense();

                        String transportType = (String) transportTypeComboBox.getSelectedItem();
                        if ("Extra".equals(transportType)) {
                            transportType = JOptionPane.showInputDialog("Enter your transportation type:");
                        }
                        double transportCost = Double.parseDouble(transportCostField.getText());

                        Transportation transport = new Transportation();
                        transport.setTransportDetails(transportType, transportCost);
                        transport.addTransportExpense();

                        tripBudgets.add(tripBudget);
                        tripBudgets.add(activities);
                        tripBudgets.add(transport);

                        header.setText("Saved Trip Detail");

                        frame.remove(budgetField);
                        frame.remove(activityNameField);
                        frame.remove(activityCostField);
                        frame.remove(transportTypeComboBox);
                        frame.remove(transportCostField);
                        frame.remove(saveButton);

                        labelBudget.setText("Total Budget: " + budget);
                        labelActivityName.setText("Activity Name: " + activityName);
                        labelActivityCost.setText("Activity Cost: " + activityCost);
                        labelTransportType.setText("Transport Type: " + transportType);
                        labelTransportCost.setText("Transport Cost: " + transportCost);

                        frame.revalidate();
                        frame.repaint();
                    }
                });

                frame.revalidate();
                frame.repaint();
            }
        });

        showTripButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder tripsDetails = new StringBuilder();
                for (Budget budget : tripBudgets) {
                    if (budget instanceof Activities) {
                        tripsDetails.append("Activity:\n");
                        ((Activities) budget).displayBudget();
                    } else if (budget instanceof Transportation) {
                        tripsDetails.append("Transportation:\n");
                        ((Transportation) budget).displayBudget();
                    } else {
                        tripsDetails.append("Budget:\n");
                        budget.displayBudget();
                    }
                }
                JOptionPane.showMessageDialog(frame, tripsDetails.toString(), "Trips Details",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}
