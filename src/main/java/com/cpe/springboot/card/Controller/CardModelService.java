package com.cpe.springboot.card.Controller;

import com.cpe.springboot.card.bus.BusService;
import com.cpe.springboot.card.model.CardEntityModel;
import com.cpe.springboot.card.model.CardReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import java.util.*;

@Service
public class CardModelService {
	
	private Random rand;

	public CardModelService() {
		this.rand=new Random();
	}

	@Autowired
	BusService busService;

	@Autowired
	private CardModelRepository cardRepository;
	
	@Autowired
	private CardReferenceService cardRefService;

	public List<CardEntityModel> getAllCardModel() {
		List<CardEntityModel> cardList = new ArrayList<>();
		cardRepository.findAll().forEach(cardList::add);
		return cardList;
	}

	public void addCard(CardEntityModel cardEntityModel) {
		cardRepository.save(cardEntityModel);
	}

	public void updateCardRef(CardEntityModel cardEntityModel) {
		cardRepository.save(cardEntityModel);

	}
	public void updateCard(CardEntityModel cardEntityModel) {
		cardRepository.save(cardEntityModel);
	}
	public Optional<CardEntityModel> getCard(Integer id) {
		return cardRepository.findById(id);
	}
	
	public void deleteCardModel(Integer id) {
		cardRepository.deleteById(id);
	}
	
	public List<CardEntityModel> getRandCard(int nbr){
		List<CardEntityModel> cardList=new ArrayList<>();
		for(int i=0;i<nbr;i++) {
			CardReference currentCardRef=cardRefService.getRandCardRef();
			CardEntityModel currentCard=new CardEntityModel(currentCardRef);
			currentCard.setAttack(rand.nextFloat()*100);
			currentCard.setDefence(rand.nextFloat()*100);
			currentCard.setEnergy(100);
			currentCard.setHp(rand.nextFloat()*100);
			currentCard.setPrice(111);
			currentCard.setCardReference(cardRefService.getRandCardRef().getId());
			//save new card before sending for user creation
			//this.addCard(currentCard);
			cardList.add(currentCard);
		}
		return cardList;
	}


	@JmsListener(destination = "channelUserToCard", containerFactory = "connectionFactory")
	public void receiveUser(Integer userId, Message message) {

		System.out.println("[BUSLISTENER] [CHANNEL RESULT_BUS_MNG] RECEIVED String MSG=["+userId.toString()+"]");

		Map<String, Object> userMap = new HashMap<>();

		userMap.put("userId", userId);

		List<CardEntityModel> cardList = getRandCard(5);

		for(CardEntityModel cardEntityModel : cardList) {
			cardEntityModel.setUser(userId);
			cardRepository.save(cardEntityModel);
		}

	}


	public List<Integer> getAllCardToSell(){
		List<CardEntityModel> cardsList = this.cardRepository.findByUserId(0);
		List<Integer> cardsId = new ArrayList<>();
		for(CardEntityModel card: cardsList){
			cardsId.add(card.getId());
		}
		return cardsId;
	}

	public List<Integer> getCardsList(Integer userId){
		List<CardEntityModel> cardsList = this.cardRepository.findByUserId(userId);
		List<Integer> cardsId = new ArrayList<>();
		for(CardEntityModel card: cardsList){
			cardsId.add(card.getId());
		}
		return cardsId;
	}
}

