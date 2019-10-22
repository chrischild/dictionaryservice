/**
 * DictionaryService.java
 *
 */
package org.cannibalcoding.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cannibalcoding.dao.DictionaryDao;

import com.google.gson.Gson;

/**
 * @author Chris
 *
 */
@Path("dictionary")
public class DictionaryService {

    @Inject
    DictionaryDao dao;
    boolean foundInDictionary = Boolean.FALSE;

    @POST
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public String find(String params) {

	Gson gson = new Gson();
	HTMLElements htmlElements = gson.fromJson(params, HTMLElements.class);
	List<String> dictionary = dao.getDictionaryWords(); 	//TODO move to search method

	for (String element : htmlElements.getElements()) {
	    foundInDictionary = Boolean.FALSE;
	    search(element.toLowerCase().replace("_", ""), dictionary);

	    if (!foundInDictionary) {
		break;
	    }
	}

	return "{ \"found\": " + foundInDictionary + " }";
    }

    private void search(String element, List<String> dictionary) {

	boolean startOfWord = Boolean.FALSE;
	boolean endOfWord = Boolean.FALSE;
	for (int i = 0; i < element.length(); i++) {
	    int lengthOfAge = 3;
	    int index = element.indexOf("age");
	    String substring = "";

	    if (index > 0) {
		if (startOfWord) {
		    endOfWord = atEndOfWord(element, i, lengthOfAge);
		    substring = element.substring(0, i + lengthOfAge);
		} else {
		    startOfWord = atStartOfWord(i, index);
		    substring = element.substring(index - i, index + lengthOfAge);
		}

		checkDictionary(dictionary, substring);

		if (foundInDictionary) {
		    break;
		} else if (endOfWord) {
		    break;
		}
	    } else if (index == 0) {

		endOfWord = atEndOfWord(element, i, lengthOfAge);
		substring = element.substring(0, i + lengthOfAge);

		checkDictionary(dictionary, substring);

		if (endOfWord) {
		    break;
		}
	    }
	}
    }

    private void checkDictionary(List<String> dictionary, String substring) {
	if (dictionary.contains(substring)) {
	    this.foundInDictionary = Boolean.TRUE;
	}
    }

    private Boolean atStartOfWord(int i, int index) {
	return index - i == 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    private Boolean atEndOfWord(String element, int i, int lengthOfAge) {
	return i + lengthOfAge == element.length() ? Boolean.TRUE : Boolean.FALSE;
    }

    private class HTMLElements {

	private String[] elements;

	/**
	 * @return the elements
	 */
	public String[] getElements() {
	    return elements;
	}
    }
}
