package com.poko.pi.car.service;

import com.poko.pi.car.db.NodeRepository;
import com.poko.pi.car.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeService {

    @Autowired
    private NodeRepository repository;

    public void uploadNode(Node node) {
        repository.save(node);
    }

    public void modifyNode(Node node) {
        repository.save(node);
    }

    public void deleteNode(Node node) {
        repository.delete(node);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void bulkUpload(List<Node> nodes) {
        repository.saveAll(nodes);
    }
}
