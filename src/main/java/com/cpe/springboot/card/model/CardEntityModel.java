package com.cpe.springboot.card.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "card_model")
public class CardEntityModel implements Serializable {

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
	@Column(name = "user_id")
	public int userId;
	@Column(name = "store_id")
	public int storeId;
	@Column(name = "card_reference_id")
	public int cardReferenceId;

	public CardEntityModel() {
		super();
	}

	public CardEntityModel(CardReference cardRef) {
		super();
	}

	public CardEntityModel(String name, String description, String family, String affinity, float energy, float hp,
						   float defence, float attack, String imgUrl, String smallImg, float price) {
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

	public int getUser() {
		return this.userId;
	}

	public void setUser(int userId) {
		this.userId = userId;
	}

	public void setStore(int storeId) {
		this.storeId=storeId;
	}

	public void setCardReference(int cardReferenceId) {
		this.cardReferenceId=cardReferenceId;
	}

	public int getStore() {
		return this.storeId;
	}

	public float computePrice() {
		return this.hp * 20 + this.defence*20 + this.energy*20 + this.attack*20;
	}


	public int getCardReference() {
		return cardReferenceId;
	}

	public CardModel asCardModel() {
		CardModel cardModel = new CardModel();
		cardModel.setId(this.getId());
		cardModel.setEnergy(this.getEnergy());
		cardModel.setHp(this.getHp());
		cardModel.setDefence(this.getDefence());
		cardModel.setAttack(this.getAttack());
		cardModel.setPrice(this.getPrice());
		cardModel.setUser(this.getUser());
		cardModel.setStore(this.getStore());
		cardModel.setCardReference(this.getCardReference());
		return cardModel;
	}

}
