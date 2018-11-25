package com.example.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Players;

@Repository  
@Transactional
public interface PlayersRepository extends CrudRepository<Players, Long> {
	List<Players> findByPlayerTopicOrderByPlayerPointsDesc(String playerTopic);
	void deleteByPlayerName(String playerName);
	void deleteByPlayerTopic(String playerTopic);
}
