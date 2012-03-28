package jpa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import jpa.domain.Card;
import jpa.domain.Group;
import jpa.domain.Tag;

public class JpaCardDao implements CardDao {
	
	EntityManager em = EMF.get().createEntityManager();

	@Override
	public Card addCard(Card card) {
		em.persist(card);	
		em.refresh(card);
		return card; 
	}

	@Override
	public void removeCard(Card card) {
		em.getTransaction().begin();
		em.remove(card);
		em.getTransaction().commit();
	}

	@Override
	public Card updateCard(Card card) {
		if (card.getGaeKey() == null) {
			em.persist(card);
		} else {
			em.merge(card);			
		}
		return card;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Card> getAllCards() {
		Query query = em.createQuery("SELECT c FROM Card c");
	    return (List<Card>)query.getResultList();
	}

	@Override
	public Card findCardByKey(Key key) {
		Card card = em.find(Card.class, key);
		return card;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Card> findCardsByOwner(User owner) {
		Query query = em.createQuery("SELECT c FROM Card c WHERE owner = ?1").setParameter(1, owner);
	    return (List<Card>)query.getResultList();
	}

	@Override
	public List<Group> getAllGroupsOfCard(Card card) {
		ArrayList<Group> groupList = new ArrayList<Group>();
		for (Key groupKey : card.getGroupKeys()) {
			groupList.add(em.find(Group.class, groupKey));
		}
		return groupList;
	}
	
	@Override
	public Card addTag(Card card, Tag tag) {
		em.persist(tag);
		card.getTags().add(tag);
		em.merge(card);		
		return card;
	}

	@Override
	public Card removeTag(Card card, Tag tag) {
		em.getTransaction().begin();
		card.getTags().remove(tag);		
		em.remove(tag);
		em.getTransaction().commit();
		em.merge(card);
		return card;
	}

//	@Override
//	public Card updateTag(Card card, Tag tag) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Map<String, Tag> getAllTags(Card card) {
		HashMap<String, Tag> map = new HashMap<String, Tag>();
		List<Tag> cardList = (List<Tag>) card.getTags();
		
		System.out.println(cardList.size());
		
		for (Tag tag : cardList) {
			map.put(tag.getKey(), tag);
		}
		return map;
	}

}
