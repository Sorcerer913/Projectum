package com.example.veryness.main;

import java.util.ArrayList;
import java.util.Arrays;

public class Cellular {/*

    private int width;
    private int height;
    private int state[];
    ArrayList<int[]> states;
    private int rule;

    public Cellular(int width, int height, int rule){
        this.width = width;
        this.height = height;
        state = new int[this.width];
        Arrays.fill(state, 0);
        state[this.width /2]=1;

        states = new ArrayList<int[]>();
        states.add(state);

        this.rule = rule;

    }

    public int[][] getArray(){
        for (int it=0; it<height; it++){
            int [] ls = new int[width];
            Arrays.fill(ls, 0);
            for (int i = 0; i< width; i++){
                int index;
                if (i-1<0){
                    index = states.get(states.size()-1)[i]*2+states.get(states.size()-1)[width -1]*4+
                            states.get(states.size()-1)[(i+1)% width];
                }
                else{
                    index = states.get(states.size()-1)[i]*2+states.get(states.size()-1)[i-1]*4+
                            states.get(states.size()-1)[(i+1)% width];
                }
                ls[i]=(rule&((int)(Math.pow(2, index))))>0?1:0;

            }
            states.add(ls);
        }

        return states.toArray(new int[states.size()][]);

    }*/

}

