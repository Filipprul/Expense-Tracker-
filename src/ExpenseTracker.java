import java.util.Locale;
import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        Expense expense = new Expense();

        while (true) {
            System.out.println("\nMenü:");
            System.out.println("1. Add an expense");
            System.out.println("2. Update an expense");
            System.out.println("3. Delete an expense");
            System.out.println("4. View all expenses");
            System.out.println("5. View a summary of all expenses");
            System.out.println("6. View a summary of expenses for a specific month");
            System.out.println("7. Exit");
            System.out.print("Selection: ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Please enter a valid number!");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    expense.add();
                    break;
                case 2:
                    expense.update();
                    break;
                case 3:
                    expense.delete();
                    break;
                case 4:
                    expense.list();
                    break;
                case 5:
                    expense.summary();
                    break;
                case 6:
                    expense.monthlySummary();
                    break;
                case 7:
                    System.out.println("Exit the program");
                    return;
                default:
                    System.out.println("Invalid selection!");
            }
        }
    }
}
