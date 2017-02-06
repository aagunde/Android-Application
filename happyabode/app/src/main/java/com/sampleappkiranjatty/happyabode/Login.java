package com.sampleappkiranjatty.happyabode;

/**
 * Created by kiranjatty on 11/30/16.
 */
public class Login {
        String name;
        int role;
        public void set_name(String name){
            this.name = name;
        }
        public void set_role(int role){
            this.role = role;
        }
        public String getName(){
            return name;
        }
        public int getRole(){
            return role;
        }
    }
