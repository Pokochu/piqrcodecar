package com.poko.pi.car.controller;

import com.poko.pi.car.model.Node;
import com.poko.pi.car.service.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/nodeService")
public class NodeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeController.class);

    @Autowired
    private NodeService service;

    @PostMapping(path = "/uploadNode", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> uploadNode(@RequestBody Node node) {
        try {
            service.uploadNode(node);
            return new ResponseEntity<>(node.toString(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/modifyNode", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> modifyNode(@RequestBody Node node) {
        try {
            service.uploadNode(node);
            return new ResponseEntity<>(node.toString(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteNode", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> deleteNode(@RequestBody Node node) {
        try {
            service.deleteNode(node);
            return new ResponseEntity<>("Node deleted: " + node.getNodeName(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            service.deleteAll();
            return new ResponseEntity<>("All nodes deleted", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/bulkUpload", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> bulkUpload(@RequestBody List<Node> nodes) {
        try {
            service.bulkUpload(nodes);
            return new ResponseEntity<>("Number of nodes saved: " + nodes.size(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
