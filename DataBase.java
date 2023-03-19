package cricket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

public class DataBase {
	
	
	public static Connection dbConnection;
	List<Player> players = new ArrayList<Player>();
	List<Game> game = new ArrayList<Game>();
	List<Stats>stat = new ArrayList<Stats>();
	public static void getDBConnection() {
		if(dbConnection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "kamesh", "kamesh007");
			}catch(Exception ex) {
				System.out.println("hi");
			}
		}
	}
	
	
	
	public void getPlayerDetails(){
		Main.log.log(Level.DEBUG, " "+LocalDateTime.now()+" To get the player details"); 
		try {
			getDBConnection();
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Players");
			Roles role = null;
			
			while(rs.next()) {
				if(rs.getString(5).equals("BATSMAN")) {
					role = Roles.BATSMAN;
				}
				else if(rs.getString(5).equals("BOWLER")) {
					role = Roles.BOWLER;
				}
				else if(rs.getString(5).equals("WICKET_KEEPER")) {
					role = Roles.WICKET_KEEPER;
				}
				else if(rs.getString(5).equals("ALL_ROUNDER")) {
					role = Roles.ALL_ROUNDER;
				}
				players.add(new Player(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),role));
			}
			stmt.close();
		}catch(Exception ex) {
			Main.log.log(Level.ERROR, " "+LocalDateTime.now()+" To get the player details"); 
			System.out.println(ex.getMessage());
		}
	}
	
	public void insertData(int id, String name,String state,String phone,Roles role) {
		Main.log.log(Level.DEBUG, " "+LocalDateTime.now()+" Datas stored to table"); 
		try {
			getDBConnection();
			PreparedStatement stmt = dbConnection.prepareStatement("insert into Players values(?, ?, ?, ?, ?)");
			stmt.setInt(1, id);
			stmt.setString(2,name);
			stmt.setString(3,state);
			stmt.setString(4,phone);
			stmt.setString(5,role.toString());
			stmt.executeUpdate();
		}catch(Exception ex) {
			Main.log.log(Level.ERROR, " "+LocalDateTime.now()+" Datas stored to table"); 
			System.out.println(ex.getMessage());
		}
	}
	
	
	public void updatePlayerDetails(int id, String name,String state,String phone,Roles role) {
		System.out.println("Which detail you want update?\nName\nPhone");
			try {
				String opt = Main.sc.nextLine();
				getDBConnection();
				switch(opt) {
				case "Name":
					String names = Main.sc.nextLine();
					name = names;
					PreparedStatement pstmt = dbConnection.prepareStatement("update Players set PlayerName=? where PlayerId=?");
				pstmt.setInt(1, id);
				pstmt.setString(2,name);
				pstmt.setString(3,state);
				pstmt.setString(4,phone);
				pstmt.setString(5,role.toString());
				pstmt.executeUpdate();
				break;
				case "Phone":
					String phones = Main.sc.nextLine();
					name = phones;
					PreparedStatement pstmt2 = dbConnection.prepareStatement("update Players set PhoneNumber=? where PlayerId=?");
				pstmt2.setInt(1, id);
				pstmt2.setString(2,name);
				pstmt2.setString(3,state);
				pstmt2.setString(4,phone);
				pstmt2.setString(5,role.toString());
				pstmt2.executeUpdate();
				break;
				}
				PreparedStatement pstmt = dbConnection.prepareStatement("update Players set PlayerName=? where PlayerId=?");
				pstmt.setInt(1, id);
				pstmt.setString(2,name);
				pstmt.setString(3,state);
				pstmt.setString(4,phone);
				pstmt.setString(5,role.toString());
				pstmt.executeUpdate();

			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

	
	public void gameDetails() {
		
		try {
			Main.log.log(Level.DEBUG, " "+LocalDateTime.now()+" To get the Game details"); 
			getDBConnection();
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Game");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			while(rs.next()) {
				game.add(new Game(rs.getString(1),rs.getString(2),rs.getString(3),sdf.parse(rs.getString(4))));
			}
			stmt.close();
		}catch(Exception ex) {
			Main.log.log(Level.ERROR, " "+LocalDateTime.now()+" To get the Game details"); 
			System.out.println(ex.getMessage());
		}
	}
	
	public void gameinsertData(String matchId, String name,String place,String dates) {
		try {
			
			Main.log.log(Level.DEBUG, " "+LocalDateTime.now()+" Datas stored to table"); 
			getDBConnection();
			PreparedStatement stmt = dbConnection.prepareStatement("insert into Game values(?, ?, ?, ?)");
			stmt.setString(1,matchId);
			stmt.setString(2,name);
			stmt.setString(3,place);
			stmt.setString(4,dates);
			stmt.executeUpdate();
		}catch(Exception ex) {
			Main.log.log(Level.ERROR, " "+LocalDateTime.now()+" Datas stored to table"); 
			System.out.println(ex.getMessage());
		}
}
	
	public void scoresUpdate(int runs, int wickets,int high,int six,int four,int play,double economy,int playerId) {
		Main.log.log(Level.DEBUG, " "+LocalDateTime.now()+" Datas stored to table"); 
		try {
			getDBConnection();
			PreparedStatement stmt = dbConnection.prepareStatement("insert into Stats values(?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, runs);
			stmt.setInt(2, wickets);
			stmt.setInt(3,high);
			stmt.setInt(4,six);
			stmt.setInt(5,four);
			stmt.setInt(6, play);
			stmt.setDouble(7, economy);
			stmt.setInt(8, playerId);
			stmt.executeUpdate();
		}catch(Exception ex) {
			Main.log.log(Level.ERROR, " "+LocalDateTime.now()+" Datas stored to table"); 
			System.out.println(ex.getMessage());
		}
	}
	
public void scoreCard() {
		
		try {
			Main.log.log(Level.DEBUG, " "+LocalDateTime.now()+" To get the Stats details"); 
			getDBConnection();
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Stats");
			
			while(rs.next()) {
				stat.add(new Stats(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),rs.getInt(7),rs.getInt(8)));
			}
			stmt.close();
		}catch(Exception ex) {
			Main.log.log(Level.ERROR, " "+LocalDateTime.now()+" To get the Stats details"); 
			System.out.println(ex.getMessage());
		}
		
	}
	
}	
