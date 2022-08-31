package learn.jwt.andsocket.controller;

import learn.jwt.andsocket.model.dto.ChatRoomDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {


    @ResponseBody
    @PostMapping("/api/room/enter")
    public ResponseEntity<?> enterTheRoom(@RequestBody ChatRoomDTO chatRoomDTO){

    }
}
