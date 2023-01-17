package org.example;
import java.sql.*;

public class ConnectionToDB {
    private Connection openConnection(){
        String jdbcURL = "jdbc:postgresql://localhost:5432/StudentsList";
        String userName = "postgres";
        String password = "19975519";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL,userName,password);
            System.out.println("Good! There is connection");
            return connection;
        } catch (SQLException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }

    }
    public void insert(String Name,String lastName,String Phone){

        try {
            String query="INSERT INTO students_list(first_name,last_name,phone_number)"+
                    "VALUES('"+Name+"','"+lastName+"','"+Phone+"')";
            Connection connection=openConnection();
            System.out.println("connection good");
            Statement statement=connection.createStatement();
            System.out.println("Insert Good");
            int readable =statement.executeUpdate(query);
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void veiwTable(){
        try {
            String query="SELECT * FROM students_list;";
            Connection connection=openConnection();
            System.out.println("connection Good");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            System.out.println("Select print Good");
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String Name=resultSet.getString("first_name");
                String lastName=resultSet.getString("last_name");
                String phoneNumber=resultSet.getString("phone_number");
                System.out.println("ID: "+id+"  First Name:  "+Name+" Last Name: "+lastName+" Phone: "+phoneNumber);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
            throw new RuntimeException(e);
        }
    }
    public void Update(String Name,String lastName,String Phone,int ConditionId){
        try {
            String query=String.format("UPDATE \"students_list\""+
                    "SET first_name='%s',last_name='%s',phone_number='%s'"+
                    "WHERE id= '%s'",Name,lastName,Phone,Integer.toString(ConditionId));
            Connection connection=openConnection();
            Statement statement= connection.createStatement();
            int resultSet=statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteRow(int id){
        try {
            String query = String.format("DELETE FROM \"students_list\"" +
                    "WHERE id = '%s'", Integer.toString(id));
            Connection connection = openConnection();
            Statement statement = connection.createStatement();
            int resulteSet = statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    }