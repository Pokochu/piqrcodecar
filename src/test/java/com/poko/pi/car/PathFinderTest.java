package com.poko.pi.car;

import com.poko.pi.car.model.Graph;
import com.poko.pi.car.model.Node;
import com.poko.pi.car.pathfinding.EuclideanScorer;
import com.poko.pi.car.pathfinding.PathFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PathFinderTest {

    private Graph<Node> map;
    private PathFinder<Node> pathFinder;

    @BeforeEach
     void setup() {
        Set<Node> nodes = new HashSet<>();
        Map<String, Set<String>> edges = new HashMap<>();

        nodes.add(new Node(1.0, 1.0, "first", false));
        nodes.add(new Node(1.0, 6.0, "second", false));
        nodes.add(new Node(1.0, 10.5, "third", false));
        nodes.add(new Node(3.5, 8, "fourth", false));
        nodes.add(new Node(4, 12, "fifth", false));
        nodes.add(new Node(7.84, 10.5, "sixth", false));

        edges.put("first", Stream.of("second").collect(Collectors.toSet()));
        edges.put("second", Stream.of("third", "fourth", "fifth").collect(Collectors.toSet()));
        edges.put("third", Stream.of("fourth", "fifth", "sixth").collect(Collectors.toSet()));
        edges.put("fourth", Stream.of("fifth", "sixth").collect(Collectors.toSet()));

        map =  new Graph<>(nodes, edges);
        pathFinder = new PathFinder<>(map, new EuclideanScorer(), new EuclideanScorer());
    }

    @Test
    public void testPathFinder() {
        List<Node> path = pathFinder.findPath(map.getNode("first"), map.getNode("sixth"));

        assertEquals(4, path.size());
        assertEquals("sixth", path.get(path.size() - 1).getId());
    }
}
