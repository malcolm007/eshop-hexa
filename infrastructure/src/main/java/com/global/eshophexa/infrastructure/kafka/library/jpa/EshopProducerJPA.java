package com.global.eshophexa.infrastructure.kafka.library.jpa;

import com.global.eshophexa.infrastructure.kafka.library.models.KafkaEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EshopProducerJPA {
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_NEW_EVENTS = "Select * from  ";
    private static final String WHERE_CLAUSE = " where status='KO' OR status IS null";

    private static final String UPDATE_STATUS = "UPDATE %s SET status = ? WHERE ID = ?";

    public List<KafkaEventDTO> findAllEventToSend(String producerName) {
        String query = FIND_NEW_EVENTS + getTableName(producerName) + WHERE_CLAUSE;
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        return mapData(result);
    }


    public List<KafkaEventDTO> mapData(List<Map<String, Object>> rs) {
        return rs.stream().map(m -> {
            KafkaEventDTO kafkaEventDTO = new KafkaEventDTO();
            kafkaEventDTO.setId(Long.valueOf(m.get("ID").toString()));
            kafkaEventDTO.setEntityId(Long.valueOf(m.get("ENTITYID").toString()));
            kafkaEventDTO.setStatus((String) m.get("STATUS"));
            return kafkaEventDTO;
        }).toList();
    }

    public String getTableName(String producerName) {
        return "ESHOP_" + producerName + "_EVENTS";
    }

    public void updateStatus(String producerName, List<KafkaEventDTO> kafkaEventDTOs) {
        kafkaEventDTOs.forEach(kafkaEventDTO -> {
            String query = String.format(UPDATE_STATUS, getTableName(producerName));
            jdbcTemplate.update(query, kafkaEventDTO.getStatus(), kafkaEventDTO.getId());
        });
    }
}
