package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.anish.calabash.Calabash;
import com.anish.calabash.DFS;
import com.anish.calabash.World;

import asciiPanel.AsciiPanel;
import maze.Node;

public class WorldScreen implements Screen {

    private World world;

    String[] sortSteps;
    Calabash bro;

    public WorldScreen() {
        world = new World();
        bro=new Calabash(new Color(0,0,255), 1, world);
        world.put(bro, 0, 0);
    }


    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i=0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch(key.getKeyCode()){
            case 37:{
                if(world.isInArea(bro.getX()-1, bro.getY())){
                    bro.tryMove(bro.getX()-1, bro.getY());
                }
                break;
            }
            case 38:{
                if(world.isInArea(bro.getX(), bro.getY()-1)){
                    bro.tryMove(bro.getX(), bro.getY()-1);
                }
                break;
            }
            case 39:{
                if(world.isInArea(bro.getX()+1, bro.getY())){
                    bro.tryMove(bro.getX()+1, bro.getY());
                }
                break;
            }
            case 40:{
                if(world.isInArea(bro.getX(), bro.getY()+1)){
                    bro.tryMove(bro.getX(), bro.getY()+1);
                }
                break;
            }
            case 10:{
                if(i< world.getPlan().size())
                    bro.tryMove(world.getPlan().get(i).x,world.getPlan().get(i).y);
                    i+=1;
            }
        }


        return this;
    }

}
