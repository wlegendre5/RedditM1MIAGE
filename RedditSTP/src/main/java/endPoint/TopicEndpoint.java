
package endPoint;

import java.util.ArrayList;
import java.util.List;

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

import entity.TopicEntity;

@Api(name = "topicendpoint")
public class TopicEndpoint {
	
	public TopicEndpoint() {
		
	}
	
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
		
		Key key = KeyFactory.createKey("Topic", sender);
		Entity t = new Entity("Topic", key);
		
		t.setProperty("sender",sender);
		t.setProperty("title",title);
		t.setProperty("id",key);
		t.setProperty("lien",lien);
		t.setProperty("body",body);
		t.setProperty("karma",0);

		datastore.put(t);
	}
	
	@ApiMethod(
	        path = "list/{limit}",
	        httpMethod = HttpMethod.GET
	    )
	public ArrayList<TopicEntity> getListTopic(
			@Named("limit") Integer limit){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		ArrayList<TopicEntity> list = new ArrayList<>();
        
		Query q = new Query("Topic").addSort("Date", SortDirection.DESCENDING);
		q.setKeysOnly();

		PreparedQuery pq = datastore.prepare(q);
		
		List<Entity> results = pq.asList(FetchOptions.Builder.withLimit(limit));
		for (Entity r : results) {
			try {
				Entity e = datastore.get(r.getParent());
				TopicEntity t = new TopicEntity();
				t.setId(e.getProperty("id").toString());
				t.setKarma(Integer.valueOf( e.getProperty("karma").toString()));
				t.setSender( e.getProperty("sender").toString());
				ArrayList<String> voters = (ArrayList<String>) e.getProperty("voters");
				t.setVoters(voters);
				t.setLien(e.getProperty("lien").toString());
				t.setTitle(e.getProperty("title").toString());
				t.setBody(e.getProperty("body").toString());
				
				list.add(t);
			} catch (EntityNotFoundException e) {
				return null;
			}
		}
		return list;
	}
	
	
	

}

