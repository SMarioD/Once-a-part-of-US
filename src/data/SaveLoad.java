package data;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.io.*;
import java.sql.*;

public class SaveLoad {
    GamePanel gp;
    private Connection conn;
    public SaveLoad(GamePanel gp, String dbPath) {
        this.gp=gp;

        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(url);
            initializeDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void initializeDatabase() {
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Saves (" +
                    "maxLife INTEGER," +
                    "life INTEGER," +
                    "maxEnergy INTEGER," +
                    "energy INTEGER," +
                    "hasSoul INTEGER," +
                    "mapPlayerX INTEGER," +
                    "mapPlayerY INTEGER," +
                    "currentMap INTEGER);";
            stmt.execute(sql);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public Entity getObjects(String itemName)
    {
        Entity obj=null;

        switch (itemName)
        {
            case "Boots": obj=new Obj_Boots(gp);break;
            case "Chest": obj=new Obj_Chest(gp);break;
            case "Door": obj=new Obj_Door(gp);break;
            case "Energy Crystal": obj=new Obj_EnergyCrystal(gp);break;
            case "Heart": obj=new Obj_Heart(gp);break;
            case "Key": obj=new Obj_Key(gp);break;
            case "Soul": obj=new Obj_Soul(gp);break;
        }
        return obj;
    }

    public void save()
    {
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("save.data")));

            DataStorage ds=new DataStorage();

            try {
                conn.setAutoCommit(false);
                String delete,sql = "INSERT INTO Saves (maxLife, life, maxEnergy, energy,  hasSoul,mapPlayerX,mapPlayerY,currentMap) ";
                sql+="VALUES ("+gp.player.maxLife+","+gp.player.life+","+gp.player.maxEnergy +","+gp.player.energy+","+gp.player.hasSoul+","+ gp.player.worldX+","+ gp.player.worldY+","+ gp.currentMap+");";
                Statement st=conn.createStatement();
                delete="DELETE FROM Saves";
                st.executeUpdate(delete);
                conn.commit();
                st.executeUpdate(sql);
                conn.commit();
                st.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }

            //OBJECTS ON MAP
            ds.mapObjectNames=new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX=new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY=new int[gp.maxMap][gp.obj[1].length];

            for(int mapNum=0;mapNum<gp.maxMap;mapNum++)
            {
                for(int i=0;i<gp.obj[1].length;i++)
                {
                    if(gp.obj[mapNum][i]==null)
                    {
                        ds.mapObjectNames[mapNum][i]="NA";

                    }
                    else
                    {
                        ds.mapObjectNames[mapNum][i]=gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i]=gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i]=gp.obj[mapNum][i].worldY;
                    }
                }
            }


            //Write the DataStorage oject
            oos.writeObject(ds);

        }
        catch (Exception e)
        {
           System.out.println("Save Exception!");
        }
    }

    public void load()
    {

        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("save.data")));

            //Read the DataStorage onject
            DataStorage ds=(DataStorage)ois.readObject();
            try{
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM Saves ");
                if(rs.next())
                {
                    gp.player.life=rs.getInt("life");
                    gp.player.maxLife=rs.getInt("maxLife");
                    gp.player.energy=rs.getInt("energy");
                    gp.player.maxEnergy=rs.getInt("maxEnergy");
                    gp.player.hasSoul=rs.getInt("hasSoul");
                    gp.player.worldX=rs.getInt("mapPlayerX");
                    gp.player.worldY=rs.getInt("mapPlayerY");
                    gp.currentMap=rs.getInt("currentMap");
                }
            }catch(SQLException e)
            {
                e.printStackTrace();
            }

            //OBJECTS ON MAP
            for(int mapNum=0;mapNum<gp.maxMap;mapNum++)
            {
                for(int i=0;i<gp.obj[1].length;i++)
                {
                    if(ds.mapObjectNames[mapNum][i].equals("NA"))
                    {
                        gp.obj[mapNum][i]=null;
                    }
                    else
                    {
                        gp.obj[mapNum][i]=getObjects(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX=ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY=ds.mapObjectWorldY[mapNum][i];
                        if(ds.mapObjectNames[mapNum][i]!= null)
                        {
                            gp.obj[mapNum][i]=getObjects(ds.mapObjectNames[mapNum][i]);
                        }
                    }
                }
            }

        }
        catch (Exception e)
        {
            System.out.println("Load Exception!");
        }
    }
}
