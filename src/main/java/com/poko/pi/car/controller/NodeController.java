package com.poko.pi.car.controller;

import com.poko.pi.car.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/nodeService")
public class NodeController {

    @Autowired
    private NodeService service;

    @PostMapping(value = "/uploadNode")
    public void uploadNode() {

    }

    @PostMapping(value = "/modifyNode")
    public void modifyNode() {

    }

    @DeleteMapping(value = "/deleteNode")
    public void deleteNode() {

    }

    @PostMapping(value = "/bulkUpload")
    public void bulkUpload() {

    }
}
