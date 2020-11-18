package com.poko.pi.car.model;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<T extends GraphNode> {

    private final Set<T> nodes;
    private final Map<String, Set<String>> edges;

    public Graph(Set<T> nodes, Map<String, Set<String>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public T getNode(String id) {
        return nodes.stream()
                .filter(node -> node.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No node found with ID: " + id));
    }

    public Set<T> getConnections(T node) {
        return edges.get(node.getId()).stream()
                .map(this::getNode)
                .collect(Collectors.toSet());
    }
}
