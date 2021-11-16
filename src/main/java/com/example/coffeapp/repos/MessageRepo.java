package com.example.coffeapp.repos;

import com.example.coffeapp.models.Message;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);

}
