package com.example.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.PlayersRepository;
import com.example.entity.Players;

@Controller
public class PlayersController {

	@Autowired
	private PlayersRepository playerRepository;
	
	@RequestMapping(value="/save", method=RequestMethod.PUT, consumes="text/plain")
	@ResponseBody
	public String save(@RequestBody String param){
		try{
			JSONObject json = new JSONObject(param);
			Players player = new Players(json.getString("playerName"), json.getInt("playerPoints"), json.getString("playerTopic"));
			playerRepository.save(player);
		}catch(Exception e){
			return "Error: "+e.getLocalizedMessage();
		}
		return "1";
	}
	
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	@ResponseBody
	public Iterable<Players> getPlayersList(){
		return playerRepository.findAll();
	}
	
	@RequestMapping(value="/get/{topic}", method=RequestMethod.GET)
	@ResponseBody
	public Iterable<Players> getPlayersList(@PathVariable String topic){
		return playerRepository.findByPlayerTopicOrderByPlayerPointsDesc(topic);
	}
	
	@RequestMapping(value="/deletebyname/{name}", method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteByName(@PathVariable String name){
		try{
			playerRepository.deleteByPlayerName(name);
		}catch(Exception e){
			return "Error: "+e.getLocalizedMessage();
		}
		return "1";
	}
	
	@RequestMapping(value="/deletebytopic/{topic}", method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteByTopic(@PathVariable String topic){
		try{
			playerRepository.deleteByPlayerTopic(topic);
		}catch(Exception e){
			return "Error: "+e.getLocalizedMessage();
		}
		return "1";
	}
	
}