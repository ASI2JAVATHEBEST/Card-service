package com.cpe.springboot.card.model;

public class CardLightEntityModel extends CardEntityModel {

    private float energy;
    private float hp;
    private float defence;
    private float attack;
    private float price;

    private Integer userId;
    private Integer storeId;
    private Integer cardReferenceId;

    public CardLightEntityModel() {

    }

    public CardLightEntityModel(CardEntityModel cModel) {
        super();
        this.energy=cModel.getEnergy();
        this.hp=cModel.getHp();
        this.defence=cModel.getDefence();
        this.attack=cModel.getAttack();
        this.price=cModel.getPrice();
        this.userId = cModel.getUser();
        this.storeId = cModel.getStore();
        this.cardReferenceId = cModel.getCardReference();
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCardReferenceId() {
        return cardReferenceId;
    }

    public void getCardReferenceId(Integer cardReferenceId) {
        this.cardReferenceId = cardReferenceId;
    }
}
