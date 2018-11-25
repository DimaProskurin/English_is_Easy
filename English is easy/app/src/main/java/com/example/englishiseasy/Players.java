package com.example.englishiseasy;

public class Players {
	private long id;
	private String playerName;
	private int playerPoints;
	private String playerTopic;
	
	public Players() {} 
	
	public Players(String playerName, int playerPoints, String playerTopic){
		super(); 
		this.playerName = playerName;
		this.playerPoints = playerPoints;
		this.playerTopic = playerTopic;
	}

	public Players(long id, String playerName, int playerPoints, String playerTopic){
		super();
		this.id = id;
		this.playerName = playerName;
		this.playerPoints = playerPoints;
		this.playerTopic = playerTopic;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerPoints() {
		return playerPoints;
	}

	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}

	public String getPlayerTopic() {
		return playerTopic;
	}

	public void setPlayerTopic(String playerTopic) {
		this.playerTopic = playerTopic;
	}
}
