package sample;

import javafx.collections.ObservableList;

import java.sql.*;

import static sample.ContactControler.phonebook;

public class Database {
    public static final String DB_NAME="database.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:"+DB_NAME;
    public static String TABLE;


    public static final String  COLUMN_NAME="name";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_BIRTH="birth";

    ContactControler contactControler=new ContactControler();

    public static void setTable(String email){
       TABLE= email;
    }

    public void save(String name,String number,String email,String birthday){
        try {

            Connection con = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = con.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS "+TABLE+" (name TEXT,phone TEXT,email TEXT ,birth TEXT)");
            PreparedStatement p = con.prepareStatement("INSERT INTO "+TABLE+" (name,phone,email,birth)" +
                    "VALUES(?,?,?,?)");
            p.setString(1,name);
            p.setString(2,number);
            p.setString(3,email);
            p.setString(4,birthday);
            p.executeUpdate();
            Phonebook item=new Phonebook(name,number,email,birthday);
            phonebook.add(item);


        } catch (SQLException e) {
            System.out.println("notworking" + e.getMessage());
        }
    }
    public static ObservableList<Phonebook> load() {
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = con.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS "+TABLE+" (name TEXT,phone TEXT,email TEXT ,birth TEXT)");

            statement.execute("SELECT * FROM "+TABLE);
            ResultSet result= statement.getResultSet();
            while (result.next()){
                String nam= result.getString("name");
                String phn= result.getString("phone");
                String eml= result.getString("email");
                String birthh= result.getString("birth");

                Phonebook item=new Phonebook(nam,phn,eml,birthh);
                phonebook.add(item);
            }
            return phonebook;
        }
        catch (SQLException e) {
            System.out.println("not working load" + e.getMessage());
        }
        return null;
    }

    public static void Delete(String phone,int index){
        try {

            Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = con.createStatement();
            PreparedStatement p = con.prepareStatement("DELETE FROM "+TABLE+" WHERE phone=?");
            p.setString(1,phone);
            p.executeUpdate();

            phonebook.remove(index);
        }
        catch (SQLException e) {
            System.out.println("Delete not working" + e.getMessage());
        }
    }

    public static ObservableList<Phonebook> Update(int indexx, String phone, String newname, String newphn, String neweml, String newbirth) {
        try {

            Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = con.createStatement();
            PreparedStatement p = con.prepareStatement("UPDATE "+TABLE+" SET name=?,phone=?,email=?,birth=?"
                    + "WHERE phone=?");
            p.setString(1, newname);
            p.setString(2, newphn);
            p.setString(3, neweml);
            p.setString(4, newbirth);
            p.setString(5, phone);
            p.executeUpdate();
            phonebook.remove(indexx);
            Phonebook item = new Phonebook(newname, newphn, neweml, newbirth);
            phonebook.add(item);

        } catch (SQLException e) {
            System.out.println("Update not working" + e.getMessage());
        }
        return null;
    }
//------------------------------------------------------------------------------------------------------------------------------------
        //Saving sign Up
    public void Saveid(String name, String phn, String email, String pass){

        String TABLE=email;
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = con.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS data (name TEXT,phone TEXT,email TEXT ,pass TEXT)");

            PreparedStatement ps = con.prepareStatement("INSERT INTO data (name,phone,email,pass)" +
                    "VALUES(?,?,?,?)");
            ps.setString(1,name);
            ps.setString(2,phn);
            ps.setString(3,email);
            ps.setString(4,pass);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("notworking" + e.getMessage());
        }

    }

}
