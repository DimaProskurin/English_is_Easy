package com.example.entity;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Players{
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String playerName;
	
	@Column(nullable = false)
	private int playerPoints;
	
	@Column(nullable = false)
	private String playerTopic;
	
	public Players() {} 
	
	public Players(String playerName, int playerPoints, String playerTopic){
		super(); 
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
