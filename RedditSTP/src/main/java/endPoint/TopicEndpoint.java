
package endPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.repackaged.com.google.protobuf.LazyStringArrayList;

import entity.TopicEntity;

/*
 * Classe TopicEndpoint
 * @author DELANOU Quentin & LEGENDRE William
 * 
 * API REST associée à la solution logicielle Reddit-m1miage 
 * Une implémentation de cette API est faite sur la couche AngularJS de notre solution
 * Il est possible d'accéder à l'API à partir du lien suivant : 
 * https://apis-explorer.appspot.com/apis-explorer/?base=https://reddit-m1miage.appspot.com/_ah/api#p/topicendpoint/v1/
 * 
 */

@Api(name = "topicendpoint")
public class TopicEndpoint {
	
	//Création d'un topic
	@ApiMethod(
	        path = "create",
	        httpMethod = HttpMethod.POST
	    )
	public void createTopic(
			@Named("title") String title, 
			@Named("body") String body,
			@Named("sender") String sender,
			@Named("lien") String lien) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Random r = new Random();
		int r_id = r.nextInt(999999);

		Entity t = new Entity("Topic");
	
		t.setProperty("sender",sender);
		t.setProperty("title",title);
		t.setProperty("id", "t" + r_id);
		t.setProperty("lien",lien);
		t.setProperty("body",body);
		t.setProperty("karma",0);

		datastore.put(t);
	}
	
	//Récupération de tous les topics stockés dans le Datastore
	//Une implémentation de cette méthode est faite dans le cadre de l'affichage des topics sur la page de d'accueil
	@ApiMethod(
	        path = "list/{limit}",
	        httpMethod = HttpMethod.GET
	    )
	public ArrayList<TopicEntity> getListTopic(
		@Named("limit") Integer limit){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
		Query q = new Query("Topic");
		PreparedQuery pq = datastore.prepare(q);
		List<Entity> topics = pq.asList(FetchOptions.Builder.withLimit(limit));
		ArrayList<TopicEntity> results = new ArrayList<TopicEntity>();
		
		for (Entity r : topics){
				TopicEntity t = new TopicEntity();
					t.setId(r.getProperty("id").toString());
					t.setKarma(Integer.valueOf( r.getProperty("karma").toString()));
					t.setSender(r.getProperty("sender").toString());
					//A tester
					ArrayList<String> voters = (ArrayList<String>) r.getProperty("voters");
					t.setVoters(voters);
					t.setLien(r.getProperty("lien").toString());
					t.setTitle(r.getProperty("title").toString());
					t.setBody(r.getProperty("body").toString());
				results.add(t);
		}
		return results;
	}
	
	//Récupération d'un topic à partir de son id
	@ApiMethod(
	        path = "get/{id}",
	        httpMethod = HttpMethod.GET
	    )
	public Entity getOneTopic(
		@Named("id") String id){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
		Filter f = new FilterPredicate("id", FilterOperator.EQUAL, id);
		Query q = new Query("Topic").setFilter(f);
		
		PreparedQuery pq = datastore.prepare(q);
		Entity res = pq.asSingleEntity();

		return res;
	}
	
	//Récupération des 100 topics ayant un karma supérieur ou égal à celui du score passé en paramètre
	//Les topics retournés sont classés en fonction de leur karma par ordre descendant
	@ApiMethod(
	        path = "toptopics/{score}",
	        httpMethod = HttpMethod.GET
	    )
	public ArrayList<TopicEntity> getTopTopics(
		@Named("score") Integer score){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
		Filter f = new FilterPredicate("karma", FilterOperator.GREATER_THAN_OR_EQUAL, score);
		Query q = new Query("Topic").setFilter(f).addSort("karma", Query.SortDirection.DESCENDING);
		PreparedQuery pq = datastore.prepare(q);
		List<Entity> topics = pq.asList(FetchOptions.Builder.withLimit(100));
		ArrayList<TopicEntity> results = new ArrayList<TopicEntity>();
		
		for (Entity r : topics){
				TopicEntity t = new TopicEntity();
					t.setId(r.getProperty("id").toString());
					t.setKarma(Integer.valueOf( r.getProperty("karma").toString()));
					t.setSender(r.getProperty("sender").toString());
					//A tester
					ArrayList<String> voters = (ArrayList<String>) r.getProperty("voters");
					t.setVoters(voters);
					t.setLien(r.getProperty("lien").toString());
					t.setTitle(r.getProperty("title").toString());
					t.setBody(r.getProperty("body").toString());
				results.add(t);
		}
		return results;
	}
	
	
	//Méthode permettant l'upvote d'un topic à partir de son id
	@ApiMethod(
	        path = "upvote/{idTopic}",
	        httpMethod = HttpMethod.GET
	    )
	public void upvoteTopic(
			@Named("idTopic") String idTopic){
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

			Filter keyFilter = new FilterPredicate("id", FilterOperator.EQUAL, idTopic);
			Query q = new Query("Topic").setFilter(keyFilter);
			List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults().limit(1));
			
			Entity result = results.get(0);
			int karma = Integer.parseInt(result.getProperty("karma").toString());
			result.setProperty("karma", karma+1);
			//TODO Ajouter l'utilisateur dans la liste des voters du topic
	        datastore.put(result);
		}


	//Méthode permettant le downvote d'un topic à partir de son id
	@ApiMethod(
	        path = "downvote/{idTopic}",
	        httpMethod = HttpMethod.GET
	    )
	public void downvoteTopic(
			@Named("idTopic") String idTopic){
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
			Filter keyFilter = new FilterPredicate("id", FilterOperator.EQUAL, idTopic);
			Query q = new Query("Topic").setFilter(keyFilter);
			List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults().limit(1));
			
			Entity result = results.get(0);
			int karma = Integer.parseInt(result.getProperty("karma").toString());
			result.setProperty("karma", karma-1);
			//TODO Retirer l'utilisateur de la liste des voters du topic
	        datastore.put(result);
		}
}
	
	
	

