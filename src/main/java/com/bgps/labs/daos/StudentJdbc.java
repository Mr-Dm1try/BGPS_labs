package com.bgps.labs.daos;

import com.bgps.labs.models.Student;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentJdbc {
    private final JdbcTemplate jdbc;

    public StudentJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Student get(int id){
        return jdbc.queryForObject("select * from STUDENT where id = ?", new StudentMapper(), id);
    }

    public List<Student> getAll(){
        return jdbc.query("select * from STUDENT", new StudentMapper());
    }

    public List<Student> getByGroupId(int id)
    {
        return jdbc.query("select * from STUDENT WHERE study_group_id = ?", new StudentMapper(), id);
    }

    public int add(@NotNull Student std){
        return jdbc.update("insert into STUDENT (SURNAME, NAME, SECOND_NAME, STUDY_GROUP_ID) values (?, ?, ?, ?)",
                std.getSurname(), std.getName(), std.getSecondName(), std.getStudyGroupId());
    }

    public int update(@NotNull Student std){
        StringBuilder sql = new StringBuilder("update STUDENT set ");
        if (!StringUtils.isEmpty(std.getSurname()))
            sql.append("surname = '").append(std.getSurname()).append("', ");
        if (!StringUtils.isEmpty(std.getName()))
            sql.append("name = '").append(std.getName()).append("', ");
        if (!StringUtils.isEmpty(std.getSecondName()))
            sql.append("second_name = '").append(std.getSecondName()).append("', ");
        if (std.getStudyGroupId() != null)
            sql.append("study_group_id = '").append(std.getStudyGroupId()).append("' ");
        else if (sql.charAt(sql.length() - 2) == ',')
            sql.deleteCharAt(sql.length() - 2);
        sql.append("where id = ?");

        return jdbc.update(sql.toString(), std.getId());
    }

    public int delete(int id){
        return jdbc.update("delete from STUDENT where ID = ?", id);
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    class StudentMapper implements RowMapper<Student>{

        @Override
        public Student mapRow(@NotNull ResultSet rs, int i) throws SQLException {
            return new Student(
                    rs.getInt("id"),
                    rs.getString("surname"),
                    rs.getString("name"),
                    rs.getString("second_name"),
                    (Integer) rs.getInt("study_group_id")
            );
        }
    }
}
