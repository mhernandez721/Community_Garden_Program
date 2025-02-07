package gardenProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//The main program for the application

public class CommunityGardenProgram extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L; // Added for serialization warning

    private JTextField nameField, contactField; // Input fields for name and contact info
    private JComboBox<String> dateBox, timeBox; // Dropdowns for appointment date and time
    private JComboBox<String> donateTypeBox; // Dropdown for donation type
    private JTextField donateAmtField; // Input field for donation amount
    private JButton regButton, donateButton; // Buttons for registration and donation

    //All buttons and drop down commands and interfaces
    public CommunityGardenProgram() {
        setTitle("Moreno Valley Community Garden");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        // Add UI components
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Contact Info:"));
        contactField = new JTextField();
        add(contactField);

        add(new JLabel("Appointment Date:"));
        dateBox = new JComboBox<>(generateNext7Days());
        add(dateBox);

        add(new JLabel("Appointment Time:"));
        String[] times = {"8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM"};
        timeBox = new JComboBox<>(times);
        add(timeBox);

        add(new JLabel("Donation Type:"));
        String[] donateTypes = {"Monetary", "Seeds", "Soil", "Fertilizer", "Voluntary Labor", "Gardening Tools", "Other"};
        donateTypeBox = new JComboBox<>(donateTypes);
        add(donateTypeBox);

        add(new JLabel("Donation Amount (if applicable):"));
        donateAmtField = new JTextField();
        add(donateAmtField);

        regButton = new JButton("Register & Schedule Appointment");
        regButton.addActionListener(this);
        add(regButton);

        donateButton = new JButton("Make Donation");
        donateButton.addActionListener(this);
        add(donateButton);

        setVisible(true);
    }

    //Generates dates for the next 7 days
    private String[] generateNext7Days() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] dates = new String[7];
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < 7; i++) {
            dates[i] = dateFormat.format(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dates;
    }

    //Detects any inputs from the program
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == regButton) {
            // Handle registration and appointment scheduling
            String name = nameField.getText();
            String contact = contactField.getText();
            String date = (String) dateBox.getSelectedItem();
            String time = (String) timeBox.getSelectedItem();

            User user = new User(name, contact);
            Appointment appt = new Appointment(user.getName(), date, time);
            appt.schedule(); // Use interface method
            saveApptDetails(appt);
            JOptionPane.showMessageDialog(this, "Appointment Scheduled:\n" + appt.getDetails());
        } else if (e.getSource() == donateButton) {
            // Handle donation
            String type = (String) donateTypeBox.getSelectedItem();
            double amt = 0.0;

            // Use switch statement for donation type
            switch (type) {
                case "Monetary":
                    try {
                        amt = Double.parseDouble(donateAmtField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid donation amount. Please enter a number.");
                        return;
                    }
                    break;
                default:
                    amt = 0.0; // Non-monetary donations have no amount
            }

            Donation donate = new Donation(nameField.getText(), type, amt);
            donate.donate(); // Use interface method
            JOptionPane.showMessageDialog(this, "Thank you for your donation:\n" + donate.getDetails());
        }
    }

    //Saves the program inside of a text file
    private void saveApptDetails(Appointment appt) {
        try (FileWriter writer = new FileWriter("appointment_details.txt", true)) {
            writer.write(appt.getDetails() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //starts a brand new program
   
    public static void main(String[] args) {
        new CommunityGardenProgram();
    }
}
