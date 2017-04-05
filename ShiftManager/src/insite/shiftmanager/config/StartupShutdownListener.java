package insite.shiftmanager.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 
 * @author ashaikh
 * 
 */
@WebListener
public class StartupShutdownListener implements ServletContextListener {

    public StartupShutdownListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        Config.init();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        Config.dispose();
    }

}
