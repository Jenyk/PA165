package jpa.dao;

import java.util.List;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import jpa.domain.Card;
import jpa.domain.Group;

public interface GroupDao {
	
	public Group addGroup(Group group);
	
	public void removeGroup(Group group);
	
	public Group updateGroup(Group group);
	
	public List<Group> getAllGroups();
	
	public Group findGroupByKey(Key key);
	
	public List<Group> findGroupsByOwner(User owner);
	
	public Group addCardToGroup(Group group, Card card);
	
	public Group removeCardFomGroup(Group group, Card card);
	
	public List<Card> getCardsInGroup(Group group);

}
