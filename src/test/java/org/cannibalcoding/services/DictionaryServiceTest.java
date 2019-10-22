/**
 * DictionaryServiceTest.java
 *
 */
package org.cannibalcoding.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.cannibalcoding.dao.DictionaryDao;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @author Chris
 *
 */
public class DictionaryServiceTest {
    
    @Mock
    DictionaryDao dao;
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    private DictionaryService service;

    
    @Before
    public void initialize() {
	service = new DictionaryService();
	service.dao = dao;
	Mockito.when(dao.getDictionaryWords()).thenReturn(new ArrayList<String>(Arrays.asList("montage", "ageless", "advantageous")));
    }

    @Test
    public void testFoundInDictionary() {
	assertEquals("{ \"found\": false }", service.find("{elements: [\"age_gate_year\", \"montage\", \"age_gate_day\"]}"));
    }
    
    @Test
    public void testNotFoundInDictionary() {
	assertEquals("{ \"found\": true }", service.find("{elements: [\"ageless\", \"montage\", \"advantageous\"]}"));
    }
    
}
