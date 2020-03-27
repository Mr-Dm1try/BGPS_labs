package com.bgps.labs.config;

import com.bgps.labs.daos.StudentJdbc;
import net.progruzovik.bus.replication.Replicator;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
@DependsOn("busHandler")
public class BusInitializer {
    private final Replicator replicator;
    private final StudentJdbc studentJdbc;
    public BusInitializer(Replicator replicator, StudentJdbc studentJdbc) {
        this.replicator = replicator;
        this.studentJdbc = studentJdbc;
    }
    @PostConstruct
    public void init() throws IOException {
        initEntity("student", studentJdbc.getAllLocal());
    }
    private <T> void initEntity(String name, List<T> data) throws IOException {
        replicator.initializeEntity(name);
        for (T row : data) {
            replicator.addRow(name, row);
        }
    }
}
