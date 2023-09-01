import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class ItemDAOImplSQL implements ItemDAO{
    Connection con=DBCon.getMeConnection();
    @Override
    public void add(Item item) throws DAOException {
        int n=0;
        try{
            String sql="INSERT INTO items(description,stock,min_stock,cost,cat_id) "+"VALUES(?,?,?,?,?)";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,item.getItemDescription());
            stmt.setInt(2,item.getStock());
            stmt.setInt(3,item.getMinQty());
            stmt.setBigDecimal(4,item.getCost());
            Random random = null;
            stmt.setInt(5,random.nextInt(3));
            n=stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
        if(n==0){
            throw new DAOException("Item could not be inserted");
        }
    }

    @Override
    public void update(Item item) throws ItemNotFound, DAOException {
        int n=0;
        try{
            String sql="UPDATE items SET "+"description=?,stock=?,min_stock=?,cost=?,cat_id=? "+"WHERE code=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,item.getItemDescription());
            stmt.setInt(2,item.getStock());
            stmt.setInt(3,item.getMinQty());
            stmt.setBigDecimal(4,item.getCost());
            Random random = null;
            stmt.setInt(5,random.nextInt(3));
            stmt.setInt(6,item.getItemCode());
            n=stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
        if(n==0){
            throw new DAOException("Item could not be inserted");
        }
    }

    @Override
    public Item findItem(int item_code) throws ItemNotFound {
       int n=0;
       Item res=null;
       try{
           String sql="SELECT * FROM items WHERE code=?";
           PreparedStatement stmt= con.prepareStatement(sql);
           stmt.setInt(1,item_code);
           ResultSet rs=stmt.executeQuery();
           while (rs.next()){
               int item_no=rs.getInt("code");
               String description=rs.getString("description");
               int stock=rs.getInt("stock");
               int min_stock=rs.getInt("min_stock");
               BigDecimal cost=rs.getBigDecimal("cost");
               res=new Item(item_no,description,stock,min_stock,cost);
           }
       }catch (SQLException e){
           System.out.println(e);
       }
       if(res==null){
           throw  new ItemNotFound("Item is not available");
       }
       return res;
    }

    @Override
    public void delete(int item_code) throws ItemNotFound, DAOException {
        int n=0;
        try{
            String sql="DELETE FROM items WHERE code=?";
            PreparedStatement stmt= con.prepareStatement(sql);
            stmt.setInt(1,item_code);
            n=stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
        if(n==0){
            throw new DAOException("Operation Unsuccessfull");
        }
    }

    @Override
    public ArrayList<Item> getAll() throws DAOException {
        ArrayList<Item> result=new ArrayList<>();
        int n=0;
        try{
            String sql="SELECT * FROM items";
            PreparedStatement stmt= con.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();

            while(rs.next()){
                int item_no=rs.getInt("code");
                String description=rs.getString("description");
                int stock=rs.getInt("stock");
                int min_stock=rs.getInt("min_stock");
                BigDecimal cost=rs.getBigDecimal("cost");
                Item res=new Item(item_no,description,stock,min_stock,cost);
                result.add(res);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public ArrayList<Item> getAllPaginated(int page_no) throws DAOException {
        int itemsPerpage=7;
        int start=(page_no-1)*itemsPerpage;
        ArrayList<Item> result=new ArrayList<>();

        try{
            String sql="SELECT * FROM items LIMIT ?,?";
            PreparedStatement stmt= con.prepareStatement(sql);
            stmt.setInt(1,start);
            stmt.setInt(2,itemsPerpage);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                int item_no=rs.getInt("code");
                String description=rs.getString("description");
                int stock=rs.getInt("stock");
                int min_stock=rs.getInt("min_stock");
                BigDecimal cost=rs.getBigDecimal("cost");
                Item res=new Item(item_no,description,stock,min_stock,cost);
                result.add(res);
            }

        }catch (SQLException e){
            System.out.println(e);
        }
        return result;
    }

    public ArrayList<Item> getByCat(int cat_id){
        ArrayList<Item> result=new ArrayList<>();
        try{
            String sql="SELECT * FROM items WHERE cat_id=?";
            PreparedStatement stmt= con.prepareStatement(sql);
            stmt.setInt(1,cat_id);
            ResultSet rs=stmt.executeQuery();

            while(rs.next()){
                int item_no=rs.getInt("code");
                String description=rs.getString("description");
                int stock=rs.getInt("stock");
                int min_stock=rs.getInt("min_stock");
                BigDecimal cost=rs.getBigDecimal("cost");
                Item res=new Item(item_no,description,stock,min_stock,cost);
                result.add(res);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public ArrayList<Item> getLowinStock() {
        ArrayList<Item> result=new ArrayList<>();
        try{
            String sql="SELECT * FROM items WHERE stock<min_stock";
            PreparedStatement stmt= con.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();

            while(rs.next()){
                int item_no=rs.getInt("code");
                String description=rs.getString("description");
                int stock=rs.getInt("stock");
                int min_stock=rs.getInt("min_stock");
                BigDecimal cost=rs.getBigDecimal("cost");
                Item res=new Item(item_no,description,stock,min_stock,cost);
                result.add(res);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return result;
    }


}
