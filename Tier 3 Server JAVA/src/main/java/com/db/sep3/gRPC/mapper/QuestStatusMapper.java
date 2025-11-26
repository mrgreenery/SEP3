package com.db.sep3.gRPC.mapper;

import com.db.sep3.entities.QuestStatus;

public class QuestStatusMapper {

    public static QuestStatus toEntity(com.sep3.data.grpc.QuestStatus questStatus){
        return QuestStatus.valueOf(questStatus.name());
    }

    public static com.sep3.data.grpc.QuestStatus toProto(QuestStatus questStatus){
        return com.sep3.data.grpc.QuestStatus.valueOf(questStatus.name());
    }

}
