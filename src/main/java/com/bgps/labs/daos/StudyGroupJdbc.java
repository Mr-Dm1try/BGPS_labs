package com.bgps.labs.daos;

import com.bgps.labs.models.StudyGroup;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudyGroupJdbc {
    private final JdbcTemplate jdbc;

    public StudyGroupJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public StudyGroup get(int id){
        return jdbc.queryForObject("select * from STUDY_GROUP where id = ?", new StudyGroupMapper(), id);
    }

    public List<StudyGroup> getAll(){
        return jdbc.query("select * from STUDY_GROUP", new StudyGroupMapper());
    }

    public int add(@NotNull StudyGroup sg){
        return jdbc.update("insert into STUDY_GROUP (NAME) values (?)", sg.getName());
    }

    public int update(@NotNull StudyGroup sg){
        return jdbc.update("update STUDY_GROUP set name = ? where ID = ?", sg.getName(), sg.getId());
    }

    public int delete(int id){
        return jdbc.update("delete from STUDY_GROUP where ID = ?", id);
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    class StudyGroupMapper implements RowMapper<StudyGroup> {

        @Override
        public StudyGroup mapRow(@NotNull ResultSet rs, int i) throws SQLException {
            return new StudyGroup(
                    rs.getInt("id"),
                    rs.getString("name")
            );
        }
    }
}
