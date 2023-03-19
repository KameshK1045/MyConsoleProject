package cricket;

public class Stats {
    
	
	int runs;
	int wickets;
	int highestScore;
	int sixes;
	int fours;
	double economy;
	int playedMatches;
    int playerId;
	
    Stats(int run,int wicket,int high,int six, int four,double econo,int play,int id) {
    	
    	runs = run;
    	wickets = wicket;
    	highestScore = high;
    	sixes = six;
    	fours = four;
    	economy = econo;
    	playedMatches = play;
    	playerId = id;
    	
    }
	
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getPlayedMatches() {
		return playedMatches;
	}
	public void setPlayedMatches(int playedMatches) {
		this.playedMatches = playedMatches;
	}
	public int getRuns() {
		return runs;
	}
	public void setRuns(int runs) {
		this.runs = runs;
	}
	public int getHighestScore() {
		return highestScore;
	}
	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}
	public int getSixes() {
		return sixes;
	}
	public void setSixes(int sixes) {
		this.sixes = sixes;
	}
	public int getFours() {
		return fours;
	}
	public void setFours(int fours) {
		this.fours = fours;
	}
	public int getWickets() {
		return wickets;
	}
	public void setWickets(int wickets) {
		this.wickets = wickets;
	}
	public double getEconomy() {
		return economy;
	}
	public void setEconomy(int economy) {
		this.economy = economy;
	}
	
	public String toString() {
		return "Player Id : "+playerId+"\tRuns : "+runs+"\tWickets : "+wickets+"\tSixes : "+sixes+"\tFours : "+fours+"\tHighest Score :"+highestScore+"\tEconomy : "+playedMatches+"\tPlayed Matches :"+economy+"\n";
	}
}
