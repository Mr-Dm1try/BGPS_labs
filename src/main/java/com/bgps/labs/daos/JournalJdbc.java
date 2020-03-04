package com.bgps.labs.daos;

import com.bgps.labs.models.JournalEntry;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JournalJdbc {
    private final JdbcTemplate jdbc;

    public JournalJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JournalEntry get(int id){
        return jdbc.queryForObject("select * from JOURNAL where id = ?", new JournalMapper(), id);
    }

    public List<JournalEntry> getByStudent(int stId){
        return jdbc.query("select * from JOURNAL where STUDENT_ID = ?", new JournalMapper(), stId);
    }

    public List<JournalEntry> getByStudyPlan(int spId){
        return jdbc.query("select * from JOURNAL where STUDY_PLAN_ID = ?", new JournalMapper(), spId);
    }

    public List<JournalEntry> getAll(){
        return jdbc.query("select * from JOURNAL", new JournalMapper());
    }

    public int add(@NotNull JournalEntry je){
        return jdbc.update("insert into JOURNAL (STUDENT_ID, STUDY_PLAN_ID, IN_TIME, COUNT, MARK_ID) values (?, ?, ?, ?, ?)",
                je.getStudentId(), je.getStudentId(), je.isInTime(), je.getCount(), je.getMarkId());
    }

    public int update(@NotNull JournalEntry je){
        StringBuilder sql = new StringBuilder("update JOURNAL set ");
        if (je.getStudentId() != null)
            sql.append("student_id = '").append(je.getStudentId()).append("', ");
        if (je.getStudyPlanId() != null)
            sql.append("study_plan_id = '").append(je.getStudyPlanId()).append("', ");
        if (je.isInTime() != null)
            sql.append("in_time = '").append(je.isInTime()).append("', ");
        if (je.getCount() != null)
            sql.append("count = '").append(je.getCount()).append("', ");
        if (je.getMarkId() != null)
            sql.append("mark_id = ").append(je.getMarkId()).append("' ");
        else if (sql.charAt(sql.length() - 2) == ',')
            sql.deleteCharAt(sql.length() - 2);
        sql.append("where id = ?");

        return jdbc.update(sql.toString(), je.getId());
    }

    public int delete(int id){
        return jdbc.update("delete from JOURNAL where ID = ?", id);
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    class JournalMapper implements RowMapper<JournalEntry> {

        @Override
        public JournalEntry mapRow(@NotNull ResultSet rs, int i) throws SQLException {
            return new JournalEntry(
                    rs.getInt("id"),
                    rs.getInt("student_id"),
                    rs.getInt("study_plan_id"),
                    rs.getBoolean("in_time"),
                    rs.getInt("count"),
                    rs.getInt("mark_id")
            );
        }
    }
}
