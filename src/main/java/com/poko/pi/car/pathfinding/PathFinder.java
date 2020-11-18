package com.poko.pi.car.pathfinding;

import com.poko.pi.car.model.Graph;
import com.poko.pi.car.model.GraphNode;
import com.poko.pi.car.model.PathNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class PathFinder<T extends GraphNode> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathFinder.class);

    private final Graph<T> graph;
    private final Scorer<T> nextnodeScorer;
    private final Scorer<T> targetScorer;

    public PathFinder(Graph<T> graph, Scorer<T> nextnodeScorer, Scorer<T> targetScorer) {
        this.graph = graph;
        this.nextnodeScorer = nextnodeScorer;
        this.targetScorer = targetScorer;
    }

    public List<T> findPath(T from, T to) {
        Map<T, PathNode<T>> allNodes = new HashMap<>();
        Queue<PathNode> openSet = new PriorityQueue<>();

        PathNode<T> start = new PathNode<>(from, null, 0d, targetScorer.computeCost(from, to));
        allNodes.put(from, start);
        openSet.add(start);

        while ((!openSet.isEmpty())) {
            LOGGER.info("Open Set contains: {}", openSet.stream().map(PathNode::getCurrent).collect(Collectors.toSet()));
            PathNode<T> next = openSet.poll();
            LOGGER.info("Looking at node: {}", next.toString());
            if(next.getCurrent().equals(to)) {
                LOGGER.info("Destination found...");
                List<T> route = new ArrayList<>();
                PathNode<T> current = next;
                do {
                    route.add(0, current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                } while (current != null);

                LOGGER.info("Route: {}", route.toString());
                return route;
            }

            graph.getConnections(next.getCurrent()).forEach(connection -> {
                double newScore = next.getRouteScore() + nextnodeScorer.computeCost(next.getCurrent(), connection);
                PathNode<T> nextNode = allNodes.getOrDefault(connection, new PathNode<>(connection));
                allNodes.put(connection, nextNode);
                if (nextNode.getRouteScore() > newScore) {
                    nextNode.setPrevious(next.getCurrent());
                    nextNode.setRouteScore(newScore);
                    nextNode.setEstimatedScore(newScore + targetScorer.computeCost(connection, to));
                    openSet.add(nextNode);
                    LOGGER.info("Found a better route to node: {}", nextNode.toString());
                }
            });
        }
        throw new IllegalStateException("No route found from: " + from.toString() + ", to: " + to.toString());
    }
}
