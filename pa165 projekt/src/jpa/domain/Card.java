package jpa.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key gaeKey;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Tag> tags;

	private File img;
	private User owner;
	private boolean privacy;
	private Date created;
	
	@ManyToMany(mappedBy="cards")
	private Set<Key> groupKeys;

	public Card(File img, User owner, boolean privacy) {
		this.setImg(img);
		this.setOwner(owner);
		this.setPrivacy(privacy);
		this.tags = new ArrayList<Tag>();
		this.setCreated(new Date());
		this.setGroupKeys(new HashSet<Key>());
	}

	public Card(String imgPath, User owner, boolean privac) {
		this.setImg(new File(imgPath));
		this.setOwner(owner);
		this.setPrivacy(privacy);
		this.setCreated(created);
		this.tags = new ArrayList<Tag>();
		this.setCreated(new Date());
		this.setGroupKeys(new HashSet<Key>());
	}

	public Key getGaeKey() {
		return gaeKey;
	}

	public void setGaeKey(Key key) {
		this.gaeKey = key;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isPrivacy() {
		return privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	// pristupovat pres CardDao.getAllGroupsOfCard(Card card)
	public Set<Key> getGroupKeys() {
		return groupKeys;
	}

	public void setGroupKeys(Set<Key> groups) {
		this.groupKeys = groups;
	}

}
