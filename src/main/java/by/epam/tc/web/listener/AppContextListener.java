package by.epam.tc.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.tc.web.controller.constant.Constant;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPool;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPoolException;

public class AppContextListener implements ServletContextListener {
	private static final Logger logger = LogManager.getLogger(by.epam.tc.web.listener.AppContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			logger.fatal("connection pool not created", e);
			sce.getServletContext().setAttribute(Constant.Utility.ERROR, e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionPool.getInstance().dispose();
	}

}
