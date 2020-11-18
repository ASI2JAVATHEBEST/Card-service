package com.cpe.springboot.card.model;

import com.cpe.springboot.store.model.StoreModel;
import com.cpe.springboot.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "card_model")
public class CardModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer id;
	@Column(name = "energy")
	public float energy;
	@Column(name = "hp")
	public float hp;
	@Column(name = "defence")
	public float defence;
	@Column(name = "attack")
	public float attack;
	@Column(name = "price")
	public float price;

	//@ManyToOne(fetch = FetchType.LAZY)
	//@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "user_id", nullable = true)
	@ManyToOne
	@JoinColumn
	public UserModel user;

	@ManyToOne
	@JoinColumn
	public StoreModel store;

	@ManyToOne
	@JoinColumn
	public CardReference cardReference;

	public CardModel() {
		super();
	}

	public CardModel(CardReference cardRef) {
		super();
	}

	public CardModel(String name, String description, String family, String affinity, float energy, float hp,
					 float defence, float attack,String imgUrl,String smallImg,float price) {
		super();
		this.energy = energy;
		this.hp = hp;
		this.defence = defence;
		this.attack = attack;
		this.price=price;
	}
	public float getEnergy() {
		return energy;
	}
	public void setEnergy(float energy) {
		this.energy = energy;
	}
	public float getHp() {
		return hp;
	}
	public void setHp(float hp) {
		this.hp = hp;
	}
	public float getDefence() {
		return defence;
	}
	public void setDefence(float defence) {
		this.defence = defence;
	}
	public float getAttack() {
		return attack;
	}
	public void setAttack(float attack) {
		this.attack = attack;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public void setStore(StoreModel storeModel) {
		this.store=storeModel;
	}

	public void setCardReference(CardReference cardReference) {
		this.cardReference=cardReference;
	}

	public StoreModel getStore() {
		return store;
	}

	public float computePrice() {
		return this.hp * 20 + this.defence*20 + this.energy*20 + this.attack*20;
	}


	public CardReference getCardReference() {
		return cardReference;
	}

}
