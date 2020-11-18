package com.poko.pi.car.pathfinding;

import com.poko.pi.car.model.GraphNode;

public interface Scorer<T extends GraphNode> {
    double computeCost(T start, T destination);
}
