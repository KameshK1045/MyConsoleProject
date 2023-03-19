package cricket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;



public class Main {
	
	static Scanner sc = new Scanner(System.in);
	ArrayList<Player> players = new ArrayList<Player>();
	DataBase db = new DataBase();
	static Logger log = Logger.getLogger(Main.class.getName());  
	BCCImanager bcci = new BCCImanager();
//------------------------------------------------------------------------------------------------------------------------------------------------	
	
	public void getPlayersDetails(){
		
		
		Roles role = null;
		File file = new File("/home/kamesh-zstk323/eclipse-workspace/Myproject/src/cricket/Players.csv");
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		while(true) {
			String name = "";
			
			try {
				name = reader.readLine();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			if(name==null) {
				break;
			}
			
			String[] playerInfo = name.split(",");
			
			if(playerInfo[0].equals("Info")) {
				continue;
			}
			
			else if(playerInfo[0].equals("Player")) {
				try {
					if(playerInfo[5].equals("BATSMAN")) {
						role = Roles.BATSMAN;
					}
					else if(playerInfo[5].equals("BOWLER")) {
						role = Roles.BOWLER;
					}
					else if(playerInfo[5].equals("WICKET_KEEPER")) {
						role = Roles.WICKET_KEEPER;
					}
					else if(playerInfo[5].equals("ALL_ROUNDER")) {
						role = Roles.ALL_ROUNDER;
					}
					
					
					players.add(new Player(Integer.parseInt(playerInfo[1]), playerInfo[2], playerInfo[3],playerInfo[4],role));
				} 
				catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
			}
		}
		
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void signUp() {
		Main.log.log(Level.DEBUG, " "+LocalDateTime.now()+" Enter the signUp method"); 
		String state = "";
		String phone = "";
		String regex = "[a-zA-Z\s]+";
		String regex2 = "[0-9]{10}";
		Roles role = null;
		
		System.out.print("Enter your ID : ");
		int id = sc.nextInt();
		sc.nextLine();
		if(!db.players.contains(id)) {
		System.out.print("Enter your Name : ");
		String name = sc.nextLine();
		if(name.matches(regex)) {
			System.out.print("Enter your State : ");
		    state = sc.nextLine();
		    if(state.matches(regex)) {
		       System.out.print("Enter your Mobile Number : ");
		       phone = sc.nextLine();
		       System.out.println("Enter your role : "+"\n"+"1.Batsman\n2.Bowler\n3.All Rounder\n4.Wicket Keeper");
		       int rol = sc.nextInt();
		       if(rol==1) {
		    	    role = Roles.BATSMAN;
		       }
		       else if(rol==2) {
		    	   role = Roles.BOWLER;
		       }
		       else if(rol==3) {
		    	   role = Roles.ALL_ROUNDER;
		       }
		       else if(rol==4) {
		    	   role = Roles.WICKET_KEEPER;
		       }
		       if(phone.matches(regex2)) {
		    	   db.insertData(id, name, state, phone, role);
//----------------------------------------------------------------------------------------------------------------------------------------
//		    	   try (FileWriter fw = new FileWriter("/home/kamesh-zstk323/eclipse-workspace/Myproject/src/cricket/Players.csv",true)){ |
//		   			String a = "Player,"+id+","+name+","+state+","+phone+","+role+"\n";                                                   |
//		   			if(players.contains(id)) {                                                                                            |
//		   				System.out.println("It is already exists");																		  |
//		   			}																													  |
//		   			fw.write(a);																										  |	
//		   		} catch (IOException e) {																								  |
//		   			e.printStackTrace();																								  |
//		   		}           																											  |
//-----------------------------------------------------------------------------------------------------------------------------------------
		    	   
		    	   System.out.println("Successfully Registered");
		    	   main(new String[0]);
		       }
		       else {
		    	   System.out.print("Enter valid number");
		    	   phone = sc.next();
		       }
		    }
		    else {
		    	System.out.println("Enter the valid state");
		    	state = sc.next();
		    }
		}
		else {
			System.out.println("Enter valid name");
			signUp();
		}
		
		
		}
		else {
			System.out.println("Player already exist");
			signUp();
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------
	
	
	public static void main(String[] args) {
		log.log(Level.DEBUG, " "+LocalDateTime.now()+" entering main method"); 
		
		//Game game = new Game();
		Admin ad = new Admin();
		
		
		
		
		
		File frs = new File("/home/kamesh-zstk323/eclipse-workspace/Myproject/src/cricket/Game.csv");
   	    BufferedReader read = null;
   	 
   	 
   	 try{
   		 
   		 read = new BufferedReader(new FileReader(frs));
   	 } 
   	 catch (IOException e) {
			e.printStackTrace();
		 } 
   	 
   	 while(true) {
   		 String readingfile ="";
   		 
   		 try {
   			 readingfile = read.readLine();
   		 }
   		 catch (IOException e) {
				e.printStackTrace();
			}
   		 
   		 if(readingfile==null) {
   			 break;
   		 }
   		 String gamesInfo[] = readingfile.split(",");
   		 
   		 if(gamesInfo[0].equals("GameId")) {
   			 continue;
   		 }
   		 else {
   			 SimpleDateFormat sdf = new SimpleDateFormat("dd/M/YYYY");
   			 try {
   				ad.games.add(new Game(gamesInfo[0],gamesInfo[1],gamesInfo[2],sdf.parse(gamesInfo[3])));
   			 }
   			 catch (ParseException ex) {
					ex.printStackTrace();
				}
   		 }
   	 }
		
		
		
		
		
		
		
		
		
		 Main main = new Main();
		 System.out.println("\n\n\t\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~🅱🅾🅰🆁🅳 🅾🅵 🅲🅾🅽🆃🆁🅾🅻 🅵🅾🆁 🅲🆁🅸🅲🅺🅴🆃 🅸🅽 🅸🅽🅳🅸🅰~~~~~~~~~~~~~~~~~~~~~~~~~"+"\n"+"\nAdmin[A]\tPlayer[P]");
		 String admin = sc.next();
		 int option = 0;
		 
		 if(admin.equalsIgnoreCase("A")){
			 
			 System.out.print("Enter your UserName : ");
			 String user = sc.next();
			 System.out.print("Enter your password : ");
			 String pass = sc.next();
			 Main.log.log(Level.DEBUG, " "+LocalDateTime.now()+" ADMIN login");  
			 boolean one = ad.login(user, pass);
			 
			 if(one == true) {
				ad.adminWork();
			 }
			 else {
				 System.out.println("Access denied...Try Again...");
				 main(new String[0]);
			 }
		 }
		 
		 else if(admin.equalsIgnoreCase("P")) {
			 System.out.println("\nSignUp[1]"+"\t"+"SignIn[2]"+"\t"+"Press others to exit\n");
			 
			 try {
		     option = sc.nextInt();
		     
		     
			 if(option==1) {
				 main.signUp();
			 }
			 else if(option==2) {
				main.playerWork(main.signIn());
			 }
			 else {
		        System.out.println("Thanks for using.........");
		        main(new String[0]);
			 }
			 }
			 catch(Exception ex) {
				 System.out.println("Enter only numbers Try again.......");
				 main(new String[0]);
				 System.exit(0);
			 }
		 }
		 
		 else {
			 System.out.println("Enter valid details");
			 main(new String[0]);
		 }
		 
		 
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public Player signIn() {
		Main.log.log(Level.DEBUG, " "+LocalDateTime.now()+" Enter to SignIn"); 
		db.getPlayerDetails();
//		for(Player p:players) {
//			System.out.println(p);
//		}
		System.out.print("Enter your Id : ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter your Name : ");
		String playerName = sc.nextLine();
		for(Player p:db.players) {
			if(p.getPlayerId()==id && p.getPlayerName().equals(playerName) ) {
				return p;
			}
		}
		
		return null;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------
	 public void playerWork(Player p) {
		  if(p!=null) {
			  System.out.println("\nWelcome "+ p.getPlayerName());
			  System.out.println("\n1.Your Stats\n2.Match Details\n3.Exit");
			  try {
			  int playerOption = sc.nextInt();
			  switch(playerOption) {
//			  case 1:db.updatePlayerDetails(p.getPlayerId(), p.getPlayerName(), p.getState(), p.getPhone(), p.getRoles());
//			  break;
			  case 1:
				  
					  db.scoreCard();
					  for(Stats s:db.stat) {
						  if(p.getPlayerId()==s.playerId) {
							  System.out.println(s);
							  break;
						  }
					  }
					  playerWork(p);
					  break;
				  
			  case 2:db.gameDetails();
		    	 for(int i=0; i<db.game.size(); i++) {
		    		 System.out.println(db.game.get(i));
		    	 }
		    	 playerWork(p);
		    	 break;  
			  default:main(new String[0]);break;
				  
			  }
		  }
		  catch(Exception ex) {
			  System.out.println("Enter valid details");
			  playerWork(p);
		  }
		  
		 
		   
		  }
		  else {
			  System.out.println("Enter valid details");
			  main(new String[0]);
	 }
	
}}
