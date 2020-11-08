package com.poko.pi.car.controller;

import com.poko.pi.car.model.Node;
import com.poko.pi.car.service.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<String>(node.toString(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/modifyNode")
    public void modifyNode() {

    }

    @DeleteMapping(value = "/deleteNode")
    public void deleteNode() {

    }

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            service.deleteAll();
            return new ResponseEntity<String>("All nodes deleted", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(" {}", e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/bulkUpload")
    public void bulkUpload() {

    }
}
