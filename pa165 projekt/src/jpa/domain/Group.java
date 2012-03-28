package jpa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@Entity
public class Group {
	
	public Group(String name, User owner) {
		this.setName(name);
		this.setOwner(owner);
		this.setCardKeys(new HashSet<Key>());
		this.setCreated(new Date());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key gaeKey;
	
	private String name;
	private User owner;
	private Date created;
	
	@ManyToMany(mappedBy="groups")
	private Set<Key> cardKeys;

	
	public Key getGaeKey() {
		return gaeKey;
	}

	public void setGaeKey(Key key) {
		this.gaeKey = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	// pristupovat pres GroupDao.getCardsInGroup(Group group)
	public Set<Key> getCardKeys() {
		return cardKeys;
	}

	public void setCardKeys(Set<Key> cards) {
		this.cardKeys = cards;
	}
}
