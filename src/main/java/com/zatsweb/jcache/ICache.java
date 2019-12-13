package com.zatsweb.jcache;

interface ICache {

    // return value if it exists or null if it doesn't
    Object get (int key);

    // add an object to cache
    void add (int key, Object value);
}