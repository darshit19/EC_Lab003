import java.util.ArrayList;

public interface ItemDAO {
    public void add(Item item) throws DAOException;
    public void update(Item item)
            throws ItemNotFound, DAOException;
    public Item findItem(int item_code)
            throws ItemNotFound, DAOException;
    public void delete(int item_code)
            throws ItemNotFound, DAOException;
    public ArrayList<Item> getAll()
            throws DAOException;
    public ArrayList<Item> getAllPaginated(int page_no)
            throws DAOException;

    public ArrayList<Item> getByCat(int cat_id);

    public  ArrayList<Item> getLowinStock();
}
