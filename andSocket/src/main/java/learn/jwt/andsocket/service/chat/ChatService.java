package learn.jwt.andsocket.service.chat;

import learn.jwt.andsocket.model.dto.ChatRoomDTO;
import learn.jwt.andsocket.model.entity.ChatRoom;
import learn.jwt.andsocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Log4j2
@Service
@Transactional(readOnly = true)
public class ChatService {

    private Map<String,ChatRoom> chatRoomMap;

    private final ChatRoomRepository chatRoomRepository;

    @PostConstruct
    private void init(){
        chatRoomMap=new LinkedHashMap<>();
    }

    public List<ChatRoom> getFindAllRoomApi(){
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.sort(chatRooms);
        return chatRooms;
    }

    public ChatRoom getFindRoomApi(Long id){
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(ChatRoomDTO.CreateRoomDTO createRoomDTO){
        createRoomDTO.setCode(UUID.randomUUID().toString());
        ChatRoom chat = chatRoomRepository.save(createRoomDTO.toEntity());
        chatRoomMap.put(chat.getId().toString(),chat);
        return chat;
    }
}
