package com.example.demo;

import java.util.Date;

public class SysPermissions {
    private int id;
    private int available;
    private String name;
    private Date number_sort;
    private int parent_id;
    private String parent_ids;
    private String permission;
    private int resource_type;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getNumber_sort() {
        return number_sort;
    }

    public void setNumber_sort(Date number_sort) {
        this.number_sort = number_sort;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(String parent_ids) {
        this.parent_ids = parent_ids;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getResource_type() {
        return resource_type;
    }

    public void setResource_type(int resource_type) {
        this.resource_type = resource_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SysPermissions{" +
                "id=" + id +
                ", available=" + available +
                ", name='" + name + '\'' +
                ", number_sort=" + number_sort +
                ", parent_id=" + parent_id +
                ", parent_ids='" + parent_ids + '\'' +
                ", permission='" + permission + '\'' +
                ", resource_type=" + resource_type +
                ", url='" + url + '\'' +
                '}';
    }
}
