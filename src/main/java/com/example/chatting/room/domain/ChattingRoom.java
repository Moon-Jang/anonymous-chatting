package com.example.chatting.room.domain;

import com.example.chatting.common.entity.BaseEntity;
import com.example.chatting.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean isPrivate;

    @Column
    private LocalDateTime deletedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chatting_room_id", nullable = false)
    private List<ChattingParticipant> participants = new ArrayList<>();

    public ChattingRoom(String title,
                        boolean isPrivate,
                        List<ChattingParticipant> participants) {
        this.title = title;
        this.isPrivate = isPrivate;
        this.participants = participants;
    }

    public void enter(User user) {
        if (isParticipant(user.id())) {
            return;
        }

        participants.add(
            new ChattingParticipant(user.id(), user.name())
        );
    }

    public boolean isParticipant(String userId) {
        return participants.stream()
            .map(ChattingParticipant::userId)
            .anyMatch(userId::equals);
    }
}
