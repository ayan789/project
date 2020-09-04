package com.example.tlyancustomer;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	
	@PostMapping(value = "/message/sendMessage")
    public String sendMessage(@RequestBody List<MessageSendVO> messageSendVOS) {
        return "=========sendMessage=============";
    }
	
}
