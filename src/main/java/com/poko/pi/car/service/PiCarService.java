package com.poko.pi.car.service;

import com.poko.pi.car.db.EdgeRepository;
import com.poko.pi.car.db.NodeRepository;
import com.poko.pi.car.model.Graph;
import com.poko.pi.car.model.Node;
import com.poko.pi.car.model.QRCodeResult;
import com.poko.pi.car.pathfinding.EuclideanScorer;
import com.poko.pi.car.pathfinding.PathFinder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class PiCarService {

    @Autowired
    private EdgeRepository edgeRepository;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private MotorService motorService;
    @Autowired
    private QRCodeDecoderService qrCodeDecoderService;

    public void driveBetweenNodes(String from, String to) {
        PathFinder<Node> pathFinder = createPathFinder();
    }

    public void driveToNode(String destination) {
        PathFinder<Node> pathFinder = createPathFinder();
        QRCodeResult result = qrCodeDecoderService.decodeQRCode(imageService.takePicture());

        Optional<Node> destinationNode = nodeRepository.findById(destination);
    }

    private PathFinder<Node> createPathFinder() {
        Graph<Node> graph = createGraph();
        return new PathFinder<>(graph, new EuclideanScorer(), new EuclideanScorer());
    }

    private Graph<Node> createGraph() {
        return new Graph<>(getNodes(), getEdges());
    }

    private Set<Node> getNodes() {
        Set<Node> nodes = new HashSet<>();
        nodeRepository.findAll().forEach(nodes::add);
        return nodes;
    }

    private Map<String, Set<String>> getEdges() {
        Map<String, Set<String>> edges = new HashMap<>();
        edgeRepository.findAll().forEach(edge -> {
            edges.put(edge.getNodeId(), edge.getConnectedNodes());
        });
        return edges;
    }
}
