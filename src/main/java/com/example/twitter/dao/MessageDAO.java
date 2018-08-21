package com.example.twitter.dao;

import com.example.twitter.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MessageDAO extends JpaRepository<Message,Integer> {
List<Message> findByTag(String tag);
}
