package com.cpe.springboot.card.model;

import com.cpe.springboot.store.model.StoreModel;
import com.cpe.springboot.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "card_reference")
public class CardModel extends CardReference{

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

	public CardModel() {
		super();
	}

	public CardModel(CardReference cardRef) {
		super(cardRef);
	}

	public CardModel(String name, String description, String family, String affinity, float energy, float hp,
					 float defence, float attack,String imgUrl,String smallImg,float price) {
		super(name, description, family, affinity,imgUrl,smallImg);
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

	public StoreModel getStore() {
		return store;
	}

	public float computePrice() {
		return this.hp * 20 + this.defence*20 + this.energy*20 + this.attack*20;
	}


}
