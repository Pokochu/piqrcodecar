package com.poko.pi.car.service;

import com.poko.pi.car.db.EdgeRepository;
import com.poko.pi.car.model.Edge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeService {

    @Autowired
    private EdgeRepository repository;

    public void addEdge(Edge edge) {
        repository.save(edge);
    }

    public void modifyEdge(Edge edge) {
        repository.save(edge);
    }

    public Iterable<Edge> getAllEdges() {
        return repository.findAll();
    }

    public void deleteEdge(Edge edge) {
        repository.delete(edge);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void bulkUpload(List<Edge> edges) {
        repository.saveAll(edges);
    }
}
