package com.cpe.springboot.card.Controller;

import com.cpe.springboot.card.bus.BusService;
import com.cpe.springboot.card.model.CardModel;
import com.cpe.springboot.card.model.CardReference;
import com.cpe.springboot.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

	public List<CardModel> getAllCardModel() {
		List<CardModel> cardList = new ArrayList<>();
		cardRepository.findAll().forEach(cardList::add);
		return cardList;
	}

	public void addCard(CardModel cardModel) {
		cardRepository.save(cardModel);
	}

	public void updateCardRef(CardModel cardModel) {
		cardRepository.save(cardModel);

	}
	public void updateCard(CardModel cardModel) {
		cardRepository.save(cardModel);
	}
	public Optional<CardModel> getCard(Integer id) {
		return cardRepository.findById(id);
	}
	
	public void deleteCardModel(Integer id) {
		cardRepository.deleteById(id);
	}
	
	public List<CardModel> getRandCard(int nbr){
		List<CardModel> cardList=new ArrayList<>();
		for(int i=0;i<nbr;i++) {
			CardReference currentCardRef=cardRefService.getRandCardRef();
			CardModel currentCard=new CardModel(currentCardRef);
			currentCard.setAttack(rand.nextFloat()*100);
			currentCard.setDefence(rand.nextFloat()*100);
			currentCard.setEnergy(100);
			currentCard.setHp(rand.nextFloat()*100);
			currentCard.setPrice(111);
			//save new card before sending for user creation
			//this.addCard(currentCard);
			cardList.add(currentCard);
		}
		return cardList;
	}


	@JmsListener(destination = "channelUserToCard", containerFactory = "connectionFactory")
	public void receiveUser(UserModel user, Message message) {

		System.out.println("[BUSLISTENER] [CHANNEL RESULT_BUS_MNG] RECEIVED String MSG=["+user.toString()+"]");

		for(CardModel card: getRandCard(5)) {
			user.addCard(card);
		}

		busService.sendUser(user,"channelUserToCard");

	}


	public List<CardModel> getAllCardToSell(){
		return this.cardRepository.findByUser(null);
	}
}

