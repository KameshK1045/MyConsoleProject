package cricket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@SuppressWarnings("unused")
public class Admin {
	
     static String name = "RahulDravid";
     static String password = "Dravid@R19";
     Game gameId;
     Player playerId;
     List<Game> games = new ArrayList<Game>();
     List<Player>Team1 = new ArrayList<Player>();
     List<Player>Team2 = new ArrayList<Player>();
     
     
     
     Player pl = new Player();
     DataBase db = new DataBase();
     Game game = new Game();
     Main ma = new Main();
     BCCImanager bcci = new BCCImanager();
     
     //-------------------------------------------------------------------------------------------------------------------------------
     
     public boolean login(String names,String passwords) {
    	 
    	 return (Admin.name.equals(names) && Admin.password.equals(passwords))?true:false;
    	 
     }
     
    // ---------------------------------------------------------------------------------------------------------------------------------
     
     public void adminWork(){
    	 System.out.println("\n1.Players Details\n2.Enroll the game\n3.Register the Players\n4.Player Stats\n5.Result\n6.Exit");
    	 
    	 
    	 
    	 try {
    		  
    		 int a = Main.sc.nextInt();
        	 Main.sc.nextLine();
    		 switch(a) {
    			 case 1:
//    			 ma.getPlayersDetails();
//    			 for(Player p : ma.players) {
//    				 System.out.println(p);
//    			 }
    				 
    				 DataBase.getDBConnection();
        			 db.getPlayerDetails();
        			 for(Player p : db.players) {
        				 System.out.println(p);
        			 }
    				 
    			 adminWork();
    			 break;
    			 
    			 case 2:enrollGame();
    			 adminWork();
    			 break;
    			 
    			 case 3:registerGame();
    			 adminWork();
    			 break;
    			 
    			 case 4:
					  db.scoreCard();
					  for(Stats s:db.stat) {
						  System.out.println(s);
					  }
					  adminWork();
					  break;
					  
					 
    			 case 5: if(bcci.team1.size()==11 && bcci.team2.size()==11) {
					  bcci.scoreset();
		    			 adminWork();
				  }
				  else {
					  System.out.println("Register the players for match");
				  }
				 break;
				  
    			 
    			 case 6:Main.main(new String[0]);
    			 break;
    			 
    			 default:System.out.println("----------------------Enter valid option only--------------------");
    			 adminWork();
    			 break;
    			 
    			 
    		 }
    		 
    	 }
    	 catch(InputMismatchException ex) {
    		 System.out.println("----------------------Enter valid option only--------------------");
    		 adminWork();
    		 
    	 }
     }
     
     //-------------------------------------------------------------------------------------------------------------------------------
     
     public void enrollGame() {
    	 String regex = "[a-zA-Z]+";
 		 String regex2 = "[0-9]{10}";
 		 String gameId = "";
 		 String place = "";
 		 String gameName = "";
 		 String dates = "";
 		 
    	 System.out.print("Enter the Game ID : ");
    	 gameId = Main.sc.nextLine();
    	 System.out.print("Enter the place : ");
    	 place = Main.sc.nextLine();
    	 if(place.matches(regex)){
    	 System.out.print("Enter the tournament name : ");
    	 gameName = Main.sc.nextLine();
    	 System.out.print("Enter the Date(dd/MM/YYYY) : ");
    	 dates = Main.sc.nextLine();
    	 }
    
    	 System.out.println("Successfully Registered");
    	 db.gameinsertData(gameId, gameName, place, dates);
//    	 try(FileWriter fr = new FileWriter("/home/kamesh-zstk323/eclipse-workspace/Myproject/src/cricket/Game.csv",true)){
//    		 String b = ""+gameId+","+gameName+","+place+","+dates+","+"\n";
//    		 fr.write(b);
//    	 } catch (IOException e) {
//			e.printStackTrace();
//		}
    	 
     }
     
     //----------------------------------------------------------------------------------------------------------------------------------------
     
     public void registerGame() {
    	 
    	 //db.getPlayerDetails();
    	 System.out.println("Enter the Game Id : ");
    	 String gameId = Main.sc.nextLine();
    	 
         for(int i=0 ;i<games.size(); i++) {
        	 if(games.get(i).getGameId().equals(gameId)) {
        		 System.out.println("Enter the First Team Players");
        		 System.out.println("Enter the Player Id : ");
        		 while(true) {
        			 System.out.println(Team1.size());
            	 int playerId = Main.sc.nextInt();
            	 for(int j=0; j<db.players.size(); j++) {
            	 if(db.players.get(j).getPlayerId()==playerId) {
            	 Team1.add(db.players.get(j));
            	 }
            	 }
            	 if(Team1.size()==11){
            		 break;
            	 }
        		 }
        		 System.out.println("Enter the Second Team Players");
        		 while(true) {
        		 System.out.println(Team2.size());
            	 int playerId = Main.sc.nextInt();
            	 for(int j=0; j<db.players.size(); j++) {
            	 if(db.players.get(j).getPlayerId() == playerId && !Team1.contains(pl.getPlayerId())) {
            	 Team2.add(db.players.get(j));
            	 }
            	 }
            	 if(Team2.size()==11){
            		 break;
            	 }
        		 }
        	 }
         }
         
//         for(Player p:Team1) {
//        	 System.out.println(p);
//         }
//         for(Player p:Team2) {
//        	 System.out.println(p);
//         }
         
         bcci.matchReady(Team1, Team2);
     }
     
}
