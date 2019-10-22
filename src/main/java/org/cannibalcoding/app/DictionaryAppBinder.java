/**
 * AppBinder.java
 *
 */
package org.cannibalcoding.app;

import org.cannibalcoding.dao.DictionaryDao;
import org.cannibalcoding.entities.DictionaryWords;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * @author Chris
 *
 */
public class DictionaryAppBinder extends AbstractBinder{

    @Override
    protected void configure() {
	bind(DictionaryDao.class).to(DictionaryDao.class);
	bind(DictionaryWords.class).to(DictionaryWords.class);
    }
}
