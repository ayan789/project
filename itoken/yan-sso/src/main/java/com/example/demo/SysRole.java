package com.example.demo;

import java.util.List;

public class SysRole {
    private int id;
    private int available;
    private String description;
    private String role;
    private List<SysPermissions> sysPermissionsList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<SysPermissions> getSysPermissionsList() {
        return sysPermissionsList;
    }

    public void setSysPermissionsList(List<SysPermissions> sysPermissionsList) {
        this.sysPermissionsList = sysPermissionsList;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", available=" + available +
                ", description='" + description + '\'' +
                ", role='" + role + '\'' +
                ", sysPermissionsList=" + sysPermissionsList +
                '}';
    }
}
