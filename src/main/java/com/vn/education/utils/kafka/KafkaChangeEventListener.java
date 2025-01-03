package com.vn.education.utils.kafka;
import com.vn.education.dto.ChangeEvent;
import com.vn.education.utils.change.ChangeDataEvent;
import com.vn.education.utils.change.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaChangeEventListener {

    private final DatabaseService databaseService;

    @KafkaListener(topics = "dbserver1.source_db.public", groupId = "debezium-consumer-group")
    public void handleChangeEvent(String message) {
        ChangeEvent changeEvent = ChangeDataEvent.parseChangeEvent(message);
        ChangeDataEvent.processChangeEvent(changeEvent, databaseService);
    }
}

