package learn.jwt.andsocket.model.dto;

import learn.jwt.andsocket.model.entity.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDTO {
    private String roomName;
    private String roomCode;
    private int userLimit;

    @Setter
    @Getter
    @NoArgsConstructor
    public class CreateRoomDTO{
        private String name;
        private String code;
        public ChatRoom toEntity(){
            return ChatRoom.builder()
                    .roomName(name)
                    .roomCode(code)
                    .build();
        }
    }
}
