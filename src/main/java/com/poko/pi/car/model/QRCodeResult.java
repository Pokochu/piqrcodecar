package com.poko.pi.car.model;

public class QRCodeResult {

    private String nodeId;
    private double offsetX;
    private double offsetY;
    private double offsetAngle;

    public QRCodeResult(String nodeId, double offsetX, double offsetY, double offsetAngle) {
        this.nodeId = nodeId;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetAngle = offsetAngle;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    public double getOffsetAngle() {
        return offsetAngle;
    }

    public void setOffsetAngle(double offsetAngle) {
        this.offsetAngle = offsetAngle;
    }
}
