import java.math.BigDecimal;
import java.util.ArrayList;

public class InventoryService {
    ItemDAO itd;
    Item getItem(int itemno) throws ItemNotFound{
        Item res=null;
        try{
            res=itd.findItem(itemno);
        }catch (DAOException e){
            System.out.println(e);
        }

        if(res!=null){
            return res;
        }
        throw new ItemNotFound("Item Not available");

    }

    void addItem(Item item) {
        try{
            itd.add(item);
        } catch (DAOException e) {
            System.out.println(e);
        }
    }
    void updateItem(Item item){
        try{
            itd.update(item);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        } catch (ItemNotFound e) {
            throw new RuntimeException(e);
        }
    }
    void addStock(int item_code, int qty) throws ItemNotFound{
        try{
            Item temp=itd.findItem(item_code);
            temp.setQty_in_stock(qty);
            itd.update(temp);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }
    void withdrawStock(int item_code, int qty)
            throws  InSufficientStock{
        try{
            Item temp=itd.findItem(item_code);
            if(temp.getStock()<qty){
                throw new InSufficientStock("Stock is not available in the shop");
            }
        } catch (DAOException e) {
            throw new RuntimeException(e);
        } catch (ItemNotFound e) {
            throw new RuntimeException(e);
        }
    }
    void deleteItem(int item_code) throws ItemNotFound{
        try{
            itd.delete(item_code);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }
    ArrayList<Item> getAllItems() {
        ArrayList<Item> result=null;
        try{
            result=itd.getAll();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    ArrayList<Item> getAllCatItems(int cat_id){
        ArrayList<Item> result;

            result=itd.getByCat(cat_id);

        return result;
    }

    ArrayList<Item> getItemsUnderStock(){
        ArrayList<Item> result=itd.getLowinStock();
        return result;
    }

    double totalInventoryCost() throws DAOException {
        ArrayList<Item> result=itd.getAll();
       Double total = 0.0;
        for(Item it:result){
            Double stock= (double) it.getStock();
            BigDecimal value=it.getCost();
            total+=stock*value;
        }
    }
}
