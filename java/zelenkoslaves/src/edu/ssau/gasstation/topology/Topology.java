package edu.ssau.gasstation.topology;

/**
 * Created by andrey on 04.12.16.
 */
public class Topology {
    private TopologyItem[][] topology;

    public Topology(int height, int width) {
        this.topology = new TopologyItem[height][width];
    }

    public void setTopologyItem(TopologyItem item, int i, int j){
        if(i < topology.length && j < topology[0].length) {
            this.topology[i][j] = item;
        }
    }

    public TopologyItem getTopologyItem(int i, int j){
        if(i < topology.length && j < topology[0].length) {
            return this.topology[i][j];
        }
        else return null;
    }
}
