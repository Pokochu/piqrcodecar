package com.poko.pi.car.pathfinding;

import com.poko.pi.car.model.Node;

public class EuclideanScorer implements Scorer<Node> {

    @Override
    public double computeCost(Node start, Node destination) {
        double coeffX = Math.pow(destination.getX() - start.getX(), 2);
        double coeffY = Math.pow(destination.getY() - start.getY(), 2);
        return Math.sqrt(coeffX + coeffY);
    }

    public double getAngle(Node a, Node b) {
        return Math.toDegrees(Math.atan2((b.getY() - a.getY()), (b.getX() - a.getX())));
    }
}
