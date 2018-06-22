package endPoint;

import entity.PMF;
import entity.UserEntity;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "userentityendpoint", namespace = @ApiNamespace(ownerDomain = "mycompany.com", ownerName = "mycompany.com", packagePath = "services"))
public class UserEntityEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listUserEntity")
	public CollectionResponse<UserEntity> listUserEntity(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		PersistenceManager mgr = null;
		Cursor cursor = null;
		List<UserEntity> execute = null;

		try {
			mgr = getPersistenceManager();
			Query query = mgr.newQuery(UserEntity.class);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				query.setExtensions(extensionMap);
			}

			if (limit != null) {
				query.setRange(0, limit);
			}

			execute = (List<UserEntity>) query.execute();
			cursor = JDOCursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (UserEntity obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<UserEntity> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getUserEntity")
	public UserEntity getUserEntity(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		UserEntity userentity = null;
		try {
			userentity = mgr.getObjectById(UserEntity.class, id);
		} finally {
			mgr.close();
		}
		return userentity;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param userentity the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertUserEntity")
	public UserEntity insertUserEntity(UserEntity userentity) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (containsUserEntity(userentity)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.makePersistent(userentity);
		} finally {
			mgr.close();
		}
		return userentity;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param userentity the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateUserEntity")
	public UserEntity updateUserEntity(UserEntity userentity) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (!containsUserEntity(userentity)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.makePersistent(userentity);
		} finally {
			mgr.close();
		}
		return userentity;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeUserEntity")
	public void removeUserEntity(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			UserEntity userentity = mgr.getObjectById(UserEntity.class, id);
			mgr.deletePersistent(userentity);
		} finally {
			mgr.close();
		}
	}

	private boolean containsUserEntity(UserEntity userentity) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(UserEntity.class, userentity.getId());
		} catch (javax.jdo.JDOObjectNotFoundException ex) {
			contains = false;
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}
