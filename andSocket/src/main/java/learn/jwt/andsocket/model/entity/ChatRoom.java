package learn.jwt.andsocket.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatRoomId")
    private Long id;

    @Column(name = "roomName")
    private String roomName;

    @Column(name = "roomCode")
    private String roomCode;

    @Column(name = "userLimit")
    private int userLimit;

    @OneToMany(mappedBy = "chatRoom",cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessageList=new ArrayList<>();

    @Builder
    public ChatRoom(String roomName,String roomCode) {
        this.roomName = roomName;
        this.roomCode =roomCode;
    }

    public void addChatMessage(ChatMessage chatMessage){
        this.chatMessageList.add(chatMessage);
    }
}

