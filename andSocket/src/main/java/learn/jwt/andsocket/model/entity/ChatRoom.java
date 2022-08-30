package learn.jwt.andsocket.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

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

    @Builder
    public ChatRoom(String roomName,String roomCode) {
        this.roomName = roomName;
        this.roomCode =roomCode;
    }
}

