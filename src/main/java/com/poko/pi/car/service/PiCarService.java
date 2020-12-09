package com.poko.pi.car.service;

import com.poko.pi.car.db.EdgeRepository;
import com.poko.pi.car.db.NodeRepository;
import com.poko.pi.car.model.Graph;
import com.poko.pi.car.model.Node;
import com.poko.pi.car.model.PiCar;
import com.poko.pi.car.model.QRCodeResult;
import com.poko.pi.car.pathfinding.EuclideanScorer;
import com.poko.pi.car.pathfinding.PathFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PiCarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PiCarService.class);

    @Autowired
    private EdgeRepository edgeRepository;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private ImgService imageService;
    @Autowired
    private MotorService motorService;
    @Autowired
    private QRCodeDecoderService qrCodeDecoderService;

    public void driveBetweenNodes(String from, String to) throws InterruptedException {
        PathFinder<Node> pathFinder = createPathFinder();

        moveOnPath(from, pathFinder, nodeRepository.findById(to).get());
    }

    public void driveToNode(String destination) throws InterruptedException {
        PathFinder<Node> pathFinder = createPathFinder();
        Node destinationNode = nodeRepository.findById(destination).get();
        QRCodeResult result = decodeQRCode();

        moveOnPath(result.getNodeId(), pathFinder, destinationNode);
    }

    private void moveOnPath(String from, PathFinder<Node> pathFinder, Node to) throws InterruptedException {
        Node first = nodeRepository.findById(from).get();
        List<Node> path = pathFinder.findPath(first, to);

        for (Node node : path) {
            if (node != first) {
                EuclideanScorer scorer = new EuclideanScorer();
                double distance = scorer.computeCost(first, node);
                double angle = scorer.getAngle(first, node);
                LOGGER.info("Driving from {}, to {}, {} distance with {} angle...", first.getId(), node.getId(), distance, angle);
                if (angle == 90) {
                    motorService.forward(Double.valueOf(distance).longValue());
                } else if ((angle < 90) && (angle >= -90)) {
                    motorService.forward(PiCar.CAM_DIST); // camDist
                    motorService.rotateRight(Double.valueOf(angle).longValue());
                    motorService.forward(Double.valueOf(distance).longValue());
                    motorService.rotateLeft(Double.valueOf(angle).longValue());
                } else {
                    motorService.forward(PiCar.CAM_DIST); // camDist
                    motorService.rotateLeft(Double.valueOf(angle).longValue());
                    motorService.forward(Double.valueOf(distance).longValue());
                    motorService.rotateRight(Double.valueOf(angle).longValue());
                }
                QRCodeResult result = decodeQRCode();
                LOGGER.info("Should arrive to {}, actual nodeId is {}...", node.getId(), result.getNodeId());
            }
            first = node;
        }
        motorService.stop();
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

    private QRCodeResult decodeQRCode() {
        byte[] bufferedStill = imageService.takeBufferedStill();
        return qrCodeDecoderService.decodeQRCode(bufferedStill);
    }
}
