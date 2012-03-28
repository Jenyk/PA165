package jpa.dao;

import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import jpa.domain.Card;
import jpa.domain.Group;
import jpa.domain.Tag;

public interface CardDao {

	public Card addCard(Card card);
	
	public void removeCard(Card card);
	
	public Card updateCard(Card card);
	
	public List<Card> getAllCards();
		
	public Card findCardByKey(Key key);
	
	public List<Card> findCardsByOwner(User owner);
	
	public List<Group> getAllGroupsOfCard(Card card);
	
	public Card addTag(Card card, Tag tag);
	
	public Card removeTag(Card card, Tag tag);
	
//	public Card updateTag(Card card, Tag tag);
	// zbytecna metoda? (zmenit tag a zavolat updateCard())
	
	public Map<String, Tag> getAllTags(Card card);

}
