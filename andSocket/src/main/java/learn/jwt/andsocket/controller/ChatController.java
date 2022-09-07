package learn.jwt.andsocket.controller;

import learn.jwt.andsocket.config.redis.RedisPublisher;
import learn.jwt.andsocket.model.dto.ChatDTO;
import learn.jwt.andsocket.model.dto.ChatRoomDTO;
import learn.jwt.andsocket.model.entity.ChatMessage;
import learn.jwt.andsocket.principal.SecurityUtil;
import learn.jwt.andsocket.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@RequestMapping("/api/chat")
public class ChatController {

    private final RedisPublisher redisPublisher;
    private final ChatService chatService;
    private final SecurityUtil securityUtil;
    @MessageMapping("/message")
    public void message(ChatDTO chatDTO,@RequestHeader("Authorization")String accessToken){
        String username = securityUtil.returnLoginMemberInfo();

    }
}
