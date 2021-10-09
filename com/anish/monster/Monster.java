package com.anish.monster;

import java.awt.Color;
import java.util.Random;

public class Monster extends Creature implements Comparable<Monster> {

    static Monster[][] monsters;
    private int rank;
    private int rankInMatrix;

    static public void init_monsters(World world){
        
        monsters=new Monster[16][];
        for(int i=0;i<16;i++){
            monsters[i]=new Monster[16];
        }
        for(int i=0;i<256;i++){
            Monster m=new Monster(new Color((i>>4)<<4, ((i>>2) & 0xf)<<4 , (i&0xf)<<4), i, world);
            world.put(m, 10+2*(i/16), 10+2*(i%16));
        }
        Random r=new Random(System.currentTimeMillis());
        for(int i=0;i<16;i++){
            for(int j=0;j<16;j++){  
                int _rand=r.nextInt(256);
                monsters[i][j].swap(monsters[_rand/16][_rand%16]);
            }
        }
    }

    static public Monster[][] get_monsters(){
        return monsters;
    }

    public Monster(Color color, int rank, World world ) {
        super(color, (char) 2, world);
        this.rank = rank;
        this.rankInMatrix=rank;
        monsters[rank/16][rank%16]=this;
    }

    public int getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return String.valueOf(this.rank);
    }

    @Override
    public int compareTo(Monster o) {
        return Integer.valueOf(this.rank).compareTo(Integer.valueOf(o.rank));
    }

    public void swap(Monster another) {
        int x = this.getX();
        int y = this.getY();
        this.moveTo(another.getX(), another.getY());
        another.moveTo(x, y);
        int temp=this.rankInMatrix;
        this.rankInMatrix=another.rankInMatrix;
        another.rankInMatrix=temp;
        monsters[this.rankInMatrix/16][this.rankInMatrix%16]=this;
        monsters[another.rankInMatrix/16][another.rankInMatrix%16]=another;
    }
}
