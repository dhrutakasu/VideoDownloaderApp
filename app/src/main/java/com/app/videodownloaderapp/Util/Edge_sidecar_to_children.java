package com.app.videodownloaderapp.Util;

import java.util.List;

public class Edge_sidecar_to_children {
    private List<EdgesForImage> edges;

    public Edge_sidecar_to_children(List<EdgesForImage> list) {
        this.edges = list;
    }

    public List<EdgesForImage> getEdges() {
        return this.edges;
    }

    public int getImageCount() {
        return this.edges.size();
    }

    public void setEdges(List<EdgesForImage> list) {
        this.edges = list;
    }
}
