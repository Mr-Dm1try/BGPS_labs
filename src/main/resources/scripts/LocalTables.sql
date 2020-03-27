create table if not exists student_local(
    id identity not null primary key,
    surname text,
    name text,
    second_name text,
    study_group_id int,
    foreign key (study_group_id) references study_group(id) on delete set null on update cascade
);

insert into study_group (name)
values ('ИКБО-03-16');

insert into student_local (surname, name, second_name, study_group_id)
values ('Гайфулин', 'Дмитрий', 'Дмитриевич', '1'),
       ('Поляк', 'Арсений', 'Марьянович', '1'),
       ('Слинкин', 'Михаил', 'Николаевич', '1'),
       ('Каженцев', 'Василий', 'Александрович', '1');
