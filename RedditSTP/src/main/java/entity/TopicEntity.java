package entity;


import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Entity;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
@Entity
public class TopicEntity {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	String id;

	@Persistent
	String title;
	@Persistent
	String body;
	@Persistent
	ArrayList<String>  voters;
	@Persistent
	int karma;
	@Persistent
	String sender;
	@Persistent
	String lien;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public ArrayList<String> getVoters() {
		return voters;
	}
	public void setVoters(ArrayList<String> voters) {
		this.voters = voters;
	}
	public int getKarma() {
		return karma;
	}
	public void setKarma(int karma) {
		this.karma = karma;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getLien() {
		return lien;
	}
	public void setLien(String lien) {
		this.lien = lien;
	}



}

