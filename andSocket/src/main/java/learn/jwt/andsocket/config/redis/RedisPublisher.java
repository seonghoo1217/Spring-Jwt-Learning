package learn.jwt.andsocket.config.redis;

import learn.jwt.andsocket.model.dto.ChatDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class RedisPublisher {
    private final RedisTemplate<String,Object> redisTemplate;

    public void publish(ChannelTopic topic/*ChatDTO chatDTO*/){
//        redisTemplate.convertAndSend(topic.getTopic(),chatDTO);
    }

}
