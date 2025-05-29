package core;

import java.util.HashMap;
import java.util.Map;

public class Edge {
    private String sourceId;
    private String targetId;
    private String label;
    private Map<String, String> properties;

    public Edge(String sourceId, String targetId, String label, Map<String, String> properties) {
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.label = label;
        this.properties = new HashMap<String, String>(properties);
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public String getLabel() {
        return this.label;
    }

    public Map<String, String> getProps() {
        return this.properties;
    }
}
