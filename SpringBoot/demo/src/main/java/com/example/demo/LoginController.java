package com.example.demo;

import com.common.util.LocalMac;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.InetAddress;
import java.net.SocketException;

@RestController
public class LoginController {

    @GetMapping("/ping")
    public String login(){
        String macAddr = "";
        try {
            InetAddress ia = InetAddress.getLocalHost();
            System.out.println(ia);
            macAddr = LocalMac.getLocalMac(ia);
            System.out.println(macAddr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pong"+ macAddr;
    }
}
