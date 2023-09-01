import java.math.BigDecimal;

public class Item {
    private int item_code,qty_in_stock,min_qty_in_stock;
    private BigDecimal cost;
    private String description;
    //Constructors
    Item(int code,String description,int qty,int min_qty,BigDecimal cost){
        this.item_code=code;
        this.description=description;
        this.qty_in_stock=qty;
        this.min_qty_in_stock=min_qty;
        this.cost=cost;
    }

    Item(int code, String description, BigDecimal cost){
        this.item_code=code;
        this.description=description;
        this.cost=cost;
        this.qty_in_stock=0;
        this.min_qty_in_stock=0;
    }
    int getItemCode(){
        return this.item_code;
    }
    String getItemDescription(){
        return this.description;
    }
    BigDecimal getCost(){
        return this.cost;
    }
    int getStock() //gets Quantity
    {
        return this.qty_in_stock;
    }
    int getMinQty() //gets min quantity
    {
        return this.min_qty_in_stock;
    }

    void setQty_in_stock(int qty){
        this.qty_in_stock+=qty;
    }


}
