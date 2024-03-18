package com.example.admininterface.Model;

public class meet {
    private String meeting_name;
    private String meeting_venue;
    private String meeting_;
    private String department;
    private String meeting_guest;
    private String meeting_host;
    private String meeting_time;
    private String Id;

    public meet(String meeting_name, String meeting_venue, String meeting_, String department, String meeting_guest, String meeting_host, String meeting_time, String id, String meeting_description) {
        this.meeting_name = meeting_name;
        this.meeting_venue = meeting_venue;
        this.meeting_ = meeting_;
        this.department = department;
        this.meeting_guest = meeting_guest;
        this.meeting_host = meeting_host;
        this.meeting_time = meeting_time;
        Id = id;
        this.meeting_description = meeting_description;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    private  meet(){

    }

    public String getMeeting_name() {
        return meeting_name;
    }

    public void setMeeting_name(String meeting_name) {
        this.meeting_name = meeting_name;
    }

    public String getMeeting_venue() {
        return meeting_venue;
    }

    public void setMeeting_venue(String meeting_venue) {
        this.meeting_venue = meeting_venue;
    }

    public String getMeeting_() {
        return meeting_;
    }

    public void setMeeting_(String meeting_) {
        this.meeting_ = meeting_;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMeeting_guest() {
        return meeting_guest;
    }

    public void setMeeting_guest(String meeting_guest) {
        this.meeting_guest = meeting_guest;
    }

    public String getMeeting_host() {
        return meeting_host;
    }

    public void setMeeting_host(String meeting_host) {
        this.meeting_host = meeting_host;
    }

    public String getMeeting_time() {
        return meeting_time;
    }

    public void setMeeting_time(String meeting_time) {
        this.meeting_time = meeting_time;
    }

    public String getMeeting_description() {
        return meeting_description;
    }

    public void setMeeting_description(String meeting_description) {
        this.meeting_description = meeting_description;
    }

    private String meeting_description;

    public meet(String meeting_name, String meeting_venue, String meeting_, String department, String meeting_guest, String meeting_host, String meeting_time, String meeting_description) {
        this.meeting_name = meeting_name;
        this.meeting_venue = meeting_venue;
        this.meeting_ = meeting_;
        this.department = department;
        this.meeting_guest = meeting_guest;
        this.meeting_host = meeting_host;
        this.meeting_time = meeting_time;
        this.meeting_description = meeting_description;
    }
}
