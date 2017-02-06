package com.sampleappkiranjatty.happyabode;

/**
 * Created by kiranjatty on 11/30/16.
 */
public class NewRequest {

        String room, type, time, description, user, apt_no;
        byte[] byte_stream;
        int status;

        public void set_room(String room){
            this.room = room;
        }
        public void set_user(String user){
            this.user = user;
        }
        public void set_type(String type){
            this.type = type;
        }
        public void set_apt_no(String apt_no){
            this.apt_no = apt_no;
        }
        public void set_time(String time){
            this.time= time ;
        }
        public void set_description(String description){
            this.description=description;
        }
        public void set_status(int status){
            this.status = status;
        }
        public void set_byte_stream(byte[] byte_stream){
            this.byte_stream = byte_stream;
        }
        public String get_room(){
            return room;
        }
        public String get_user(){
            return user;
        }
        public String get_type(){
            return type;
        }
        public String get_apt_no(){
            return apt_no;
        }
        public String get_time(){
            return time;
        }
        public String get_description(){
            return description;
        }
        public int get_status(){
            return status;
        }
        public byte[] get_byte_stream(){return byte_stream; }
    }


