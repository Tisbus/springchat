package org.tisbus.repository;

import org.springframework.data.repository.CrudRepository;
import org.tisbus.entity.UserMessage;

public interface MessageRepo extends CrudRepository<UserMessage, Long>{}
