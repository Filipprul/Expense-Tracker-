
import java.time.LocalDate;

public class Items {
    private static int counter = 0;
    private String description;
    private double amount;
    private int id;
    private LocalDate date;

    public Items(String description, double amount){
        this.description = description;
        this.amount = amount;
        this.id = ++counter;
        this.date = LocalDate.now();
    }

    public Items(String description, double amount, LocalDate date, int id){
        this.description = description;
        this.amount = amount;
        this.id = ++counter;
        this.date = LocalDate.now();
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(Double amount){
        this.amount = amount;
    }

    public int getId(){
        return id;
    }

    public LocalDate getDate(){
        return date;
    }

    public  int getMonth(){
        return date.getMonthValue();
    }
}
