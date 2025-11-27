package com.db.sep3.gRPC.mapper;

import com.db.sep3.entities.Quest;
import com.db.sep3.entities.User;
import com.sep3.data.grpc.QuestEntity;
import com.sep3.data.grpc.UserEntity;

public class ToProto {

    public static QuestEntity QuestToProto(Quest quest) {
        QuestEntity.Builder builder = QuestEntity.newBuilder()
                .setId(quest.getId())
                .setTitle(quest.getTitle())
                .setStatus(QuestStatusMapper.toProto(quest.getStatus()))
                .setCreatedDate(TimeMapper.toTimestamp(quest.getCreatedDate()))
                .setCreatedBy(UserToProto(quest.getCreatedBy()));

        if (quest.getDescription() != null) {
            builder.setDescription(quest.getDescription());
        }
        if (quest.getAssignee() != null) {
            builder.setAssignee(UserToProto(quest.getAssignee()));
        }
        if (quest.getStartDate() != null) {
            builder.setStartDate(TimeMapper.toTimestamp(quest.getStartDate()));
        }
        if (quest.getDeadline() != null) {
            builder.setDeadline(TimeMapper.toTimestamp(quest.getDeadline()));
        }
        if (quest.getFinishedDate() != null) {
            builder.setFinishedDate(TimeMapper.toTimestamp(quest.getFinishedDate()));
        }

        return builder.build();

    }

    //util method for converting User to UserEntity
    public static UserEntity UserToProto(User user)
    {
        UserEntity userEntity = UserEntity.newBuilder()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setDisplayName(user.getDisplayName())
                .build();

        return userEntity;
    }
}
