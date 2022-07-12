package net.mst.languagemanager.objects;

public class KeyValuePair<K, V>{

    K key;
    V value;

    public KeyValuePair() {

    }

    public KeyValuePair(K key, V  value) {
        this.key = key;
        this.value = value;
    }

    public void setValue(V value) {
        this.value = value;
    }
    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }
    public K getKey() {
        return key;
    }

}
