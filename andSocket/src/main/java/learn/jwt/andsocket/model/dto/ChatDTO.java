package learn.jwt.andsocket.model.dto;

import learn.jwt.andsocket.model.entity.ChatMessage;
import learn.jwt.andsocket.model.entity.ChatRoom;
import learn.jwt.andsocket.model.entity.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatDTO {
    private Long id;

    private String writer;

    private String message;

    private MessageType messageType;

    private ChatRoom chatRoom;

    public ChatMessage toEntity(){
        return ChatMessage.builder()
                .id(id)
                .writer(writer)
                .message(message)
                .messageType(messageType)
                .chatRoom(chatRoom)
                .build();
    }
}
