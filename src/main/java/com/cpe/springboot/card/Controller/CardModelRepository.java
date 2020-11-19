package com.cpe.springboot.card.Controller;

import com.cpe.springboot.card.model.CardEntityModel;
import com.cpe.springboot.user.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardModelRepository extends CrudRepository<CardEntityModel, Integer> {
    List<CardEntityModel> findByUserId(int userId);
}
