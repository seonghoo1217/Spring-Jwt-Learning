package learn.jwt.andsocket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class ChatController {

    @ResponseBody
    @PostMapping("/api/room/enter")
    public ResponseEntity<?> enterTheRoom(@RequestBody ChatRoomDTO chatRoomDTO)
}
