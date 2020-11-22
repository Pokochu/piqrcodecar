package com.poko.pi.car.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "EDGES")
public class Edge {

    @Id
    private String nodeId;
    private Set<String> connectedNodes;

    public Edge(String nodeId, Set<String> connectedNodes) {
        this.nodeId = nodeId;
        this.connectedNodes = connectedNodes;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Set<String> getConnectedNodes() {
        return connectedNodes;
    }

    public void setConnectedNodes(Set<String> connectedNodes) {
        this.connectedNodes = connectedNodes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
