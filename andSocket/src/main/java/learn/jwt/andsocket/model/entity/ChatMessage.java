package learn.jwt.andsocket.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatId")
    private Long id;

    private MessageType messageType;

    private String writer;
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatRoomId")
    private ChatRoom chatRoom;

    public static ChatMessage createChatMessage(ChatRoom chatRoom,String writer,String message,MessageType messageType){
        return ChatMessage.builder()
                .chatRoom(chatRoom)
                .writer(writer)
                .message(message)
                .messageType(messageType)
                .build();
    }

    @Builder
    public ChatMessage(Long id, MessageType messageType, String writer, String message, ChatRoom chatRoom) {
        this.id = id;
        this.messageType = messageType;
        this.writer = writer;
        this.message = message;
        this.chatRoom = chatRoom;
    }

    public void setWriter(String writer){
        this.writer=writer;
    }

    public void setMessage(String message){
        this.message=message;
    }
}
