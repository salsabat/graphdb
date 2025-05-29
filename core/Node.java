package core;

import java.util.HashMap;
import java.util.Map;

class Node {
    private String id;
    private Map<String, String> properties;

    public Node(String id, Map<String, String> properties) {
        this.id = id;
        this.properties = new HashMap<>(properties);
    }

    public String getId() {
        return this.id;
    }

    public Map<String, String> getProps() {
        return this.properties;
    }
}