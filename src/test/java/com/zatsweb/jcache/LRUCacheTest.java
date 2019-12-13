package com.zatsweb.jcache;

import static org.junit.Assert.*;

import org.junit.Test;

public class LRUCacheTest 
{
    
    @Test
    public void checkSetGet() {
        LRUCache cache = new LRUCache(100);
        cache.add (1, "val1");
        assertEquals("val1", (String)cache.get(1));
        assertEquals(cache.size(), 1);

        cache.add (2, "val2");
        assertEquals("val2", (String)cache.get(2));
        assertEquals(cache.size(), 2);

        cache.add (2, "newval2");
        assertEquals("newval2", (String)cache.get(2));
        assertEquals(cache.size(), 2);

        assertTrue( true );
    }

    @Test
    public void checkReplace() {
        LRUCache cache = new LRUCache(3);

        // add 4 elements
        cache.add (1, "val1");
        cache.add (2, "val2");
        cache.add (3, "val3");
        cache.add (4, "val4");
        // check that 2-4 are in cache and 1 is not
        assertEquals(cache.size(), 3);
        assertEquals((String)cache.get(1), null);
        assertEquals((String)cache.get(2), "val2");
        assertEquals((String)cache.get(3), "val3");
        assertEquals((String)cache.get(4), "val4");

        // check that value of 4 gets replaced
        cache.add (4, "val4-2");
        assertEquals(cache.size(), 3);
        assertEquals((String)cache.get(4), "val4-2");

        // add one more, now expect 2 to get replaced
        cache.add (5, "val5");
        assertEquals((String)cache.get(2), null);
    }
}
