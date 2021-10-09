package com.anish.calabash;
import com.anish.calabash.Tile;
import com.anish.calabash.Floor;
import com.anish.calabash.Wall;

import maze.Node;
import com.anish.calabash.Target;
import java.util.List;
import java.util.ArrayList;

public class DFS {
    int[][] dfsCondition;
    ArrayList<Node> plan=new ArrayList<Node>();

    public void load(Tile<Thing> tiles[][]) {
        dfsCondition = new int[tiles.length][];
        for (int i = 0; i < dfsCondition.length; i++) {
            dfsCondition[i] = new int[tiles[0].length];
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getThing() instanceof Wall)
                    dfsCondition[i][j] = -1;
                else if(tiles[i][j].getThing() instanceof Target)
                    dfsCondition[i][j]=-2;
                else
                    dfsCondition[i][j] = 0;
            }
        }
    }

    public ArrayList<Node> getPlan(){
        return plan;
    }

    public void calc() {
        dfs_action(new Node(0, 0));
        
    }

    public boolean dfs_action(Node node){
        plan.add(node);
        if(dfsCondition[node.x][node.y]==-2){
            return true;
        }
        dfsCondition[node.x][node.y] = 1;
        
            if(isValid(node.x-1, node.y)){
                if(dfs_action(new Node(node.x-1, node.y)))
                    return true;
            }
            if(isValid(node.x+1, node.y)){
                if(dfs_action(new Node(node.x+1, node.y)))
                    return true;
            }
            if(isValid(node.x, node.y-1)){
                if(dfs_action(new Node(node.x, node.y-1)))
                    return true;
            }
            if(isValid(node.x, node.y+1)){
                if(dfs_action(new Node(node.x, node.y+1)))
                    return true;
            }
        dfsCondition[node.x][node.y] = 2;
        return false;
    }

    public boolean isValid(int x,int y){
        if(x<0 || x>=dfsCondition[0].length || y<0 || y>=dfsCondition.length){
            return false;
        }
        return dfsCondition[x][y]==0 || dfsCondition[x][y]==-2;
    }

}