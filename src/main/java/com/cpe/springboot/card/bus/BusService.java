package com.cpe.springboot.card.bus;

import com.cpe.springboot.card.model.CardModel;
import com.cpe.springboot.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BusService {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMsg(String msg) {
        System.out.println("[BUSSERVICE] SEND String MSG=["+msg+"]");
        jmsTemplate.convertAndSend("RESULT_BUS_MNG",msg);
    }

    public void sendMsg(String msg, String busName) {
        System.out.println("[BUSSERVICE] SEND String MSG=["+msg+"] to Bus=["+msg+"]");
        jmsTemplate.convertAndSend(busName,msg);
    }

    public void sendUser(Map user, String busName) {
        System.out.println("[BUSSERVICE] SEND String MSG=["+user.toString()+"] to Bus=["+busName+"]");
        jmsTemplate.convertAndSend(busName,user);
    }
}