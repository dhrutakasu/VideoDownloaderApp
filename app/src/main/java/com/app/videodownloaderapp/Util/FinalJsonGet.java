package com.app.videodownloaderapp.Util;

public class FinalJsonGet {
    private Graphql graphql;

    public FinalJsonGet(Graphql graphql2) {
        this.graphql = graphql2;
    }

    public Graphql getGraphql() {
        return this.graphql;
    }

    public void setGraphql(Graphql graphql2) {
        this.graphql = graphql2;
    }

    @Override
    public String toString() {
        return "FinalJsonGet{" +
                "graphql=" + graphql +
                '}';
    }
}
