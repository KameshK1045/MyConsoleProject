package cricket;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Game {

	String gameId;
	String place;
	String gameName;
	Date date;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Game(String gameId,String place,String gameName,Date date){
		this.gameId = gameId;
		this.place = place;
		this.gameName = gameName;
		this.date = date;
	}
	public Game(){}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public String toString() {
		if(gameName.length()<10 || place.length()<10){
			gameName+="\s\s\s";
			place+="\s\s\s";
					}
		return "GameId : "+gameId+"\t\tPlace : "+gameName+"\t\tTournament Name : "+place+"\t\tDate : "+sdf.format(date);
	}
}
