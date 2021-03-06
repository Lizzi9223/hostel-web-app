package by.epam.tc.web.dao.database;

import java.util.ResourceBundle;

/**
 * Gets database parameters from resource file
 * 
 * @author Lizzi9223
 *
 */
public final class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();

	private ResourceBundle bundle = ResourceBundle.getBundle(DBParameter.DB);

	private DBResourceManager() {
	}

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
}
