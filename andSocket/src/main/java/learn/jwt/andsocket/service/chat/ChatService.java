package learn.jwt.andsocket.service.chat;

import learn.jwt.andsocket.config.redis.RedisSubscriber;
import learn.jwt.andsocket.model.dto.ChatRoomDTO;
import learn.jwt.andsocket.model.dto.MemberDTO;
import learn.jwt.andsocket.model.entity.ChatRoom;
import learn.jwt.andsocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Log4j2
@Service
@Transactional(readOnly = true)
public class ChatService {

    //Message Handler
    private final RedisMessageListenerContainer redisMessageListenerContainer;

    //Subscribe Redis Handler
    private final RedisSubscriber redisSubscriber;

    private static final String CHAT_ROOMS="ON_CHAT_ROOM";
    private static final String ENTER_ROOMS="ENTER_ROOMS";

    private final RedisTemplate<String,Object> redisTemplate;
    private HashOperations<String,Long,ChatRoom> opsHashChatRoom;

    private Map<Long, ChannelTopic> topics;
    private HashOperations<String,String,String> hashOpsEnterInfo;

    @PostConstruct
    private void  init(){
        opsHashChatRoom=redisTemplate.opsForHash();
        hashOpsEnterInfo=redisTemplate.opsForHash();
        topics=new HashMap<>();
    }


    private final ChatRoomRepository chatRoomRepository;



    public List<ChatRoom> getFindAllRoomApi(){
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom getFindRoomApi(Long id){
        Optional<ChatRoom> chatRoom = Optional.ofNullable(chatRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No parameter Value!!")));
        return chatRoom.get();
    }

    /**
     *
     * @param createRoomDTO :채팅방 이름
     * 채팅방 생성: 서버간 채팅방 공유를 위해 redis hash에 저장한다.
     * @return
     */
    public ChatRoom createChatRoom(ChatRoomDTO.CreateRoomDTO createRoomDTO){
        createRoomDTO.setCode(UUID.randomUUID().toString());
        ChatRoom chat = chatRoomRepository.save(createRoomDTO.toEntity());
        opsHashChatRoom.put(CHAT_ROOMS,chat.getId(),chat);
        return chat;
    }

    /**
     * 채팅방 입장: redis에 topic을 만들고 pub/sub 통신을 하기 위한 리스너를 설정한다.
     */
    public void enterChatRoom(Long id){
        ChannelTopic topic = topics.get(id);
        if (topic==null)
            topic= new ChannelTopic(id.toString());

        redisMessageListenerContainer.addMessageListener(redisSubscriber,topic);
        topics.put(id,topic);
    }

    public ChannelTopic getTopic(Long id){
        return topics.get(id);
    }

    public void  deleteById(ChatRoom chatRoom){
        chatRoomRepository.delete(chatRoom);
    }

    /**
     * destination정보에서 roomId 추출
     */

    public String getRoomId(String destination){
        int lastIndex = destination.indexOf('/');
        if (lastIndex !=-1)
            return destination.substring(lastIndex+1);
        else
            return "";
    }

    public void setUserEnterInfo(String sessionId,Long id){
        hashOpsEnterInfo.put(ENTER_ROOMS,sessionId,id.toString());
    }

    public String getUserEnterRoomId(String sessionId){
        return hashOpsEnterInfo.get(ENTER_ROOMS,sessionId);
    }

    public void removeUserEnterInfo(String sessionId){
        hashOpsEnterInfo.delete(ENTER_ROOMS,sessionId);
    }
}
