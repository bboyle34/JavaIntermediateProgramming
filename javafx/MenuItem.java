package javafx;

public class MenuItem {

    private double price;
    public String desc;
    
    public MenuItem(double price, String desc)
    {
        this.price = price;
        this.desc = desc;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public String toString()
    {
        return this.desc + ", Price: " 
                + String.format("$%.2f", this.getPrice());
    }
    
}
