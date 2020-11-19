package com.cpe.springboot.card.Controller;

import com.cpe.springboot.card.model.CardLightEntityModel;
import com.cpe.springboot.card.model.CardEntityModel;
import com.cpe.springboot.card.model.CardReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//ONLY FOR TEST NEED ALSO TO ALLOW CROOS ORIGIN ON WEB BROWSER SIDE
@CrossOrigin
@RestController
public class CardRestController {

	

	@Autowired
	private CardModelService cardModelService;

	@Autowired
	private CardReferenceService cardReferenceService;
	
	@RequestMapping("/cards")
	private List<CardEntityModel> getAllCards() {
		List<CardEntityModel> cLightList=new ArrayList<>();
		for(CardEntityModel c:cardModelService.getAllCardModel()){
			cLightList.add(c);
		}
		return cLightList;

	}

	@RequestMapping("/cardReferences")
	private List<CardReference> getAllCardReferences() {
		List<CardReference> cList=new ArrayList<>();
		for(CardReference c:cardReferenceService.getAllCardRef()){
			cList.add(c);
		}
		return cList;

	}

	@RequestMapping("/cardReference/{id}")
	private CardReference getCardReference(@PathVariable String id) {
		Optional<CardReference> rcard;
		rcard= cardReferenceService.getCard(Integer.valueOf(id));
		if(rcard.isPresent()) {
			return rcard.get();
		}
		return null;
	}
	
	@RequestMapping("/card/{id}")
	private CardEntityModel getCard(@PathVariable String id) {
		Optional<CardEntityModel> rcard;
		rcard= cardModelService.getCard(Integer.valueOf(id));
		if(rcard.isPresent()) {
			return rcard.get();
		}
		return null;

	}
	
	@RequestMapping(method=RequestMethod.POST,value="/card")
	public void addCard(@RequestBody CardEntityModel card) {
		cardModelService.addCard(card);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/card/{id}")
	public void updateCard(@RequestBody CardEntityModel card, @PathVariable String id) {
		card.setId(Integer.valueOf(id));
		cardModelService.updateCard(card);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/card/{id}")
	public void deleteUser(@PathVariable String id) {
		cardModelService.deleteCardModel(Integer.valueOf(id));
	}

	@RequestMapping("/cards_to_sell")
	private List<Integer> getCardsToSell() {
		List<Integer> list=new ArrayList<>();
		for( Integer cardId : cardModelService.getAllCardToSell()){
			list.add(cardId);
		}
		return list;

	}

	@RequestMapping("/cards_list/{id}")
	private List<Integer> cardsList(@PathVariable Integer id) {
		List<Integer> list=new ArrayList<>();
		for( Integer cardId : cardModelService.getCardsList(id)){
			list.add(cardId);
		}
		return list;

	}

	@RequestMapping("/cards_rand")
	private List<CardLightEntityModel> getCardsRand() {
		List<CardLightEntityModel> list=new ArrayList<>();
		for( CardEntityModel c : cardModelService.getRandCard(5)){
			CardLightEntityModel cLight=new CardLightEntityModel(c);
			list.add(cLight);
		}
		return list;
	}

}
