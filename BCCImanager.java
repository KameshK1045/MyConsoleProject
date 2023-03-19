package cricket;

import java.util.ArrayList;
import java.util.List;

public class BCCImanager {
     
	private static String manager = "KabilDev";
    private static String secure = "Dev@K83";
    List<Player> team1 = new ArrayList<Player>();
    List<Player> team2 = new ArrayList<Player>();
    DataBase db = new DataBase();
    
    
    
    
    public boolean validLogin(String man,String sec) {
    	return (man.equals(manager)&&sec.equals(secure));
    }
    
    public void matchReady(List<Player> Team1,List<Player> Team2) {
    	
    	team1.addAll(Team1);
    	team2.addAll(Team2);
    	
    	System.out.println("Team1 : ");
    	for(Player p:team1) {
    		System.out.println(p.getPlayerName());
    	}
    	System.out.println("Team2 :");
    	for(Player p:team2) {
    		System.out.println(p.getPlayerName());
    	}
    	
    	if(team1.size()==11 && team2.size()==11) {
    		scoreset();
    	}
    	
    	
    }

	 public void scoreset() {
		 int total = 0;
		 int total2 = 0;
		 for(int i=0; i<team1.size(); i++) {
			 int wicket = 0;
			 int run = 0;
			 int high = 0;
			 double econo = 0;
			 int six =0;
			 int four = 0;
			 if(team1.get(i).getRoles()==Roles.BOWLER) {
				  wicket = (int)(Math.random()*5);
				  econo = Math.random()*20;
			 }
			 else if(team1.get(i).getRoles()==Roles.BATSMAN) {
			 run = (int)(Math.random()*100);
			 total+=run;
			 high = (int)(Math.random()*100);
			 six = (int)(Math.random()*10);
			 four = (int)(Math.random()*15);
			 }
			 else {
				 run = (int)(Math.random()*100);
				 total+=run;
				 high = (int)(Math.random()*100);
				 six = (int)(Math.random()*10);
				 four = (int)(Math.random()*15);
				 wicket = (int)(Math.random()*5);
				  econo = Math.random()*20;
			 }
			 int play = 0;
			 play+=1;
			 
		 int playerId = team1.get(i).getPlayerId();
		 db.scoresUpdate(run, wicket, high, six, four, play, econo, playerId);
		 }
		 for(int j=0; j<team2.size(); j++) {
			 int wicket = 0;
			 int run = 0;
			 int high = 0;
			 double econo = 0;
			 int six =0;
			 int four = 0;
			 if(team1.get(j).getRoles()==Roles.BOWLER) {
				  wicket = (int)(Math.random()*5);
				  econo = Math.random()*20;
			 }
			 else if(team1.get(j).getRoles()==Roles.BATSMAN) {
			 run = (int)(Math.random()*100);
			 total2+=run;
			 high = run+(int)(Math.random()*100);
			 six = (int)(Math.random()*10);
			 four = (int)(Math.random()*15);
			 }
			 else {
				 run = (int)(Math.random()*100);
				 total2+=run;
				 high = run + (int)(Math.random()*100);
				 six = (int)(Math.random()*10);
				 four = (int)(Math.random()*15);
				 wicket = (int)(Math.random()*5);
				  econo = Math.random()*20;
			 }
			 int play = 0;
			 play+=1;
			 int playerId = team2.get(j).getPlayerId();
			 db.scoresUpdate(run, wicket, high, six, four, play, econo, playerId);
		 }
		 
		 Result(total,total2);
	 }
	 
	 public void Result(int t1,int t2) {
		 
		 System.out.println((t1>t2)?"Team1 won":"Team2 won");
		 
	 }

}
