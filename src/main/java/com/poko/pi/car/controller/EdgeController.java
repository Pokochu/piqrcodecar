package com.poko.pi.car.controller;

import com.poko.pi.car.model.Edge;
import com.poko.pi.car.model.Node;
import com.poko.pi.car.service.EdgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/edgeService")
public class EdgeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EdgeController.class);

    @Autowired
    private EdgeService service;

    @PostMapping(path = "/addEdge", consumes = "application/json")
    public ResponseEntity<String> addEdge(@RequestBody Edge edge) {
        try {
            service.addEdge(edge);
            return new ResponseEntity<>(edge.toString(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/modifyEdge", consumes = "application/json")
    public ResponseEntity<String> modifyEdge(@RequestBody Edge edge) {
        try {
            service.modifyEdge(edge);
            return new ResponseEntity<>(edge.toString(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteEdge", consumes = "application/json")
    public ResponseEntity<String> deleteNode(@RequestBody Edge edge) {
        try {
            service.deleteEdge(edge);
            return new ResponseEntity<>("Edge deleted: " + edge.getNodeId(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            service.deleteAll();
            return new ResponseEntity<>("All edges deleted", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/bulkUpload", consumes = "application/json")
    public ResponseEntity<String> bulkUpload(@RequestBody List<Edge> edges) {
        try {
            service.bulkUpload(edges);
            return new ResponseEntity<>("Number of nodes saved: " + edges.size(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
