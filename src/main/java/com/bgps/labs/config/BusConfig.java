package com.bgps.labs.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.progruzovik.bus.dao.EntityDao;
import net.progruzovik.bus.dao.EntityJdbc;
import net.progruzovik.bus.dao.InstanceDao;
import net.progruzovik.bus.dao.InstanceJdbc;
import net.progruzovik.bus.message.*;
import net.progruzovik.bus.replication.ReplicationService;
import net.progruzovik.bus.replication.Replicator;
import net.progruzovik.bus.util.CharBufferNameConverter;
import net.progruzovik.bus.util.EntityNameConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
@PropertySource("classpath:bus.properties")
public class BusConfig {
    @Value("${bus.instance.address}")
    private String address;
    @Value("${bus.dean.address}")
    private String deanAddress;
    @Value("${bus.integration.url}")
    private String integrationUrl;
    @Bean
    public Replicator replicator(ObjectMapper mapper, Writer writer, InstanceDao
            instanceDao, EntityDao entityDao) {
        return new ReplicationService(mapper, writer, instanceDao, entityDao);
    }
    @Bean
    public RestReceiver restReceiver(RestTemplate restTemplate, BusHandler
            busHandler) {
        return new BusRestReceiver(integrationUrl, restTemplate, busHandler);
    }
    @Bean
    public BusHandler busHandler(ObjectMapper mapper, Writer writer, InstanceDao
            instanceDao, EntityDao entityDao) {
        return new MessageHandler(mapper, writer, instanceDao, entityDao,
                deanAddress);
    }
    @Bean
    public Writer writer(ObjectMapper mapper, MessageSender messageSender,
                         InstanceDao instanceDao) {
        return new MessageWriter(mapper, messageSender, instanceDao);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public MessageSender messageSender(RestTemplate restTemplate) {
        return new RestSender(address, integrationUrl, restTemplate);
    }
    @Bean
    public EntityNameConverter entityNameConverter() {
        return new CharBufferNameConverter();
    }
    @Bean
    public InstanceDao instanceDao(EntityNameConverter entityNameConverter,
                                   JdbcTemplate jdbcTemplate) {
        return new InstanceJdbc(entityNameConverter, jdbcTemplate);
    }

    @Bean
    public EntityDao entityDao(EntityNameConverter entityNameConverter, JdbcTemplate
            jdbcTemplate) {
        return new EntityJdbc(entityNameConverter, jdbcTemplate);
    }
}
