package com.anish.calabash;

import java.util.ArrayList;

import maze.*;

public class World {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    private Tile<Thing>[][] tiles;
    private ArrayList<Node> plan;

    public World() {

        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }
        int dim=WIDTH>HEIGHT ? WIDTH : HEIGHT;
        MazeGenerator mazeGenerator = new MazeGenerator(dim);
        mazeGenerator.generateMaze();

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                tiles[i][j].setThing(mazeGenerator.getMazes()[i][j]!=0 ? new Floor(this) : new Wall(this));
            }
        }
        Node target=mazeGenerator.getTarget();
        tiles[target.x][target.y].setThing(new Target(this));

        DFS dfs=new DFS();
        dfs.load(tiles);
        dfs.calc();
        plan=dfs.getPlan();
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

    public boolean isInArea(int x,int y){
        return x>=0 && x<tiles[0].length && y>=0 && y<tiles.length;
    }

    public ArrayList<Node> getPlan(){
        return plan;
    }

}
