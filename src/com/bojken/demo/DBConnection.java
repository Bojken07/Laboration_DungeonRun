package com.bojken.demo;

import java.util.ArrayList;

import java.sql.*;
import java.util.List;

public class DBConnection {


    private String URL = "jdbc:mariadb://localhost:3306/DungeonRun";

    private String USER = "root";
    private String password = "Bojkenbojken";

    Connection connection;

    public void open() {
        try {
            connection = DriverManager.getConnection(URL, USER, password);
            System.out.println("Database connected");

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection() {

        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String createTablePlayer() {
        String sql = "CREATE TABLE player (playerID INT NOT NULL AUTO_INCREMENT, name VARCHAR(60)," +
                " health INT, agility INT, level INT, currency INT, experience INT, primary KEY(playerID))";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {

            System.out.println(e);

            return "Something went wrong";

        }
        return "Table created";
    }

    public String createTableMonster() {
        String sql = "CREATE TABLE monster (monsterID INT NOT NULL AUTO_INCREMENT, type VARCHAR(100), primary KEY(monsterID))";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {

            System.out.println(e);

            return "Something went wrong";

        }
        return "Table created";
    }

    public int createPlayer(Player player) {

        int incrementID = 0;
        String sql = "INSERT INTO player (name, health, currency, agility, level, experience) values (?, ?, ?, ?, ?, ?)";

        try {


            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, player.getPlayerName());
            preparedStatement.setInt(2, player.getHealth());
            preparedStatement.setInt(3,player.getCurrency());
            preparedStatement.setInt(4, player.getAgility());
            preparedStatement.setInt(5, player.getLevel());
            preparedStatement.setInt(6, player.getExperience());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                incrementID = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return incrementID;
    }

    public int updatePlayerHealth(Player player) {

        String sql = "UPDATE player set health = ? where name = ?";
        int affectedRows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, player.getHealth());
            preparedStatement.setString(2, player.getPlayerName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public int updatePlayerAgility(Player player) {

        String sql = "UPDATE player set agility = ? where name = ?";
        int affectedRows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, player.getAgility());
            preparedStatement.setString(2, player.getPlayerName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public int updatePlayerLevel(Player player) {

        String sql = "UPDATE player set level = ? where name = ?";
        int affectedRows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, player.getLevel());
            preparedStatement.setString(2, player.getPlayerName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public int updatePlayerCurrency(Player player) {

        String sql = "UPDATE player set currency = ? where name = ?";
        int affectedRows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, player.getCurrency());
            preparedStatement.setString(2, player.getPlayerName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public List<String> getPlayer(int id) {

        String sql = "SELECT * from player where playerID = ?";
        String playerName;
        String playerHealth;
        String playerID;
        String playerAgility;
        String playerCurrency;
        String playerExp;
        String playerLevel;


        List<String> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                playerID = "PlayerID: " + rs.getString("playerid");
                playerName = "Name: " + rs.getString("name");
                playerCurrency = "Currency" + rs.getString("currency");
                playerHealth = "Health: " + rs.getString("health");
                playerAgility = "Agility: " + rs.getString("agility");
                playerExp = "Exp: " + rs.getString("experience");
                playerLevel = "Level: " + rs.getString("level");


                list.add(playerID);
                list.add(playerName);
                list.add(playerCurrency);
                list.add(playerHealth);
                list.add(playerAgility);
                list.add(playerExp);
                list.add(playerLevel);
                return list;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        return null;
    }

    public int updatePlayerExperience(Player player) {

        String sql = "UPDATE player set experience = ? where name = ?";
        int affectedRows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, player.getExperience());
            preparedStatement.setString(2, player.getPlayerName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }
    public String getPlayerWithId(int id) {

        String sql = "SELECT * from player where PlayerID = ?";
        String playerName;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                playerName = rs.getString("name");
                return playerName;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        return null;
    }

    public int getPlayerIDCount() {

        String sql = "select count(playerId) pid from player";

        int pid;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                pid = rs.getInt("pid");

                return pid;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        return 0;
    }
}