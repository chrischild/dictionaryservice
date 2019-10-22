package org.cannibalcoding.app;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Hello world!
 *
 */
public class DictionaryApp extends ResourceConfig {
    
    public DictionaryApp() {
	register(new DictionaryAppBinder());
	packages(true, "org.cannibalcoding.services");
    }
}
