package learn.jwt.andsocket.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.jwt.andsocket.model.dto.ChatDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class RedisSubscriber implements MessageListener {


    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try{
            String publicshMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            ChatDTO chatDTO = objectMapper.readValue(publicshMessage,ChatDTO.class);

            switch(chatDTO.getMessageType().toString()){
                case "CHAT":
                    messagingTemplate.convertAndSend("/sub/chat/room/"+chatDTO.getChatRoom().getId(),chatDTO);
                    break;
                case "CRROOM":
                    messagingTemplate.convertAndSend("/sub/room/create",chatDTO);
                    break;
                case "MSALL":
                    messagingTemplate.convertAndSend("/sub/chat/all",chatDTO);
                    break;
            }


        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
}
