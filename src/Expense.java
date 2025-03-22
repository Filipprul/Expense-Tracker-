import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Expense {
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    ArrayList<Items> itemList = new ArrayList<>();
    private final String FILE_NAME = "expenses.csv";

    public Expense() {
        loadFromCSV(); // Lädt Daten beim Programmstart
    }

    public void add() {
        System.out.print("--description: ");
        String description = scanner.nextLine();

        System.out.print("--amount:");
        while (!scanner.hasNextDouble()) {
            System.out.println("Please enter a valid number :");
            scanner.next();
        }
        double amount = scanner.nextDouble();
        scanner.nextLine();

        itemList.add(new Items(description, amount));
        for (Items item : itemList){
            System.out.println("Item successfully added! (ID: " + item.getId() + ") ");
        }
        saveToCSV();
    }

    public void update(){
        System.out.print("-- ID of the item to update: ");
        int idToUpdate = scanner.nextInt();
        scanner.nextLine();

        for (Items item : itemList){
            if (item.getId() == idToUpdate){
                System.out.println("-- new Description: ");
                String newDescription = scanner.nextLine();

                System.out.println("-- new Amount: ");
                double newAmount = scanner.nextDouble();
                scanner.nextLine();

                item.setDescription(newDescription);
                item.setAmount(newAmount);

                System.out.println("Item updated successfully!");
                return;
            }
        }
        System.out.println("Item not found! Please try again.");
    }

    public void delete(){
        System.out.print("-- ID of the item to delete: ");
        int idToDelete = scanner.nextInt();
        for (Items item : itemList){
            if (item.getId() == idToDelete){
                itemList.remove(idToDelete);
                System.out.println("Expense with the ID: " + idToDelete + " was deleted.");
            } else {
                System.out.println("Item with that ID not found.");
            }
        }
    }

    public void list(){
        for (Items item : itemList){
            System.out.print("\nDescription: " + item.getDescription() + ", Amount: " + item.getAmount() + " $" + ", ID: " + item.getId());
        }
    }

    public void summary(){
        double totalamount = 0;
        for (Items item : itemList) {
            totalamount += item.getAmount();
        }
        System.out.println("Total amount: " + totalamount + " $");

    }

    public void monthlySummary(){
        System.out.print("-- Give the month in the number format (1 to 12): ");
        int month = scanner.nextInt();
        scanner.nextLine();

        double monthtotal = 0;
        boolean found = false;
        System.out.println("-- The expenses for the month " + month + ": ");
        for (Items item : itemList){
            int itemMonth = item.getMonth();
            if (itemMonth == month){
                System.out.println("Description " + item.getDescription() + ", Amount: " + item.getAmount() + " $");
                monthtotal += item.getAmount();
                found = true;
            }
        }

        if (found && monthtotal > 0){
            System.out.println("The total for the month " + month + " is " + monthtotal + " $");
        } else {
            System.out.println("No expenses found for the month " + month + " .");
        }
    }

    private void saveToCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("ID,Description,Amount,Date");

            for (Items item : itemList) {
                writer.println(item.getId() + "," + item.getDescription() + "," + item.getAmount() + "," + item.getDate());
            }

            System.out.println("Expenses saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    private void loadFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Überspringt die Header-Zeile
                }

                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                int id = Integer.parseInt(parts[0]);
                String description = parts[1];
                double amount = Double.parseDouble(parts[2]);
                LocalDate date = LocalDate.parse(parts[3]);

                itemList.add(new Items(description, amount, date, id));
            }

            System.out.println("Expenses loaded from file.");
        } catch (IOException e) {
            System.out.println("No saved expenses found.");
        }
    }
}
