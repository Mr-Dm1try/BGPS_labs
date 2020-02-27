create table if not exists study_group(
    id int not null primary key,
    name text
);

create table if not exists subject(
    id int primary key,
    name text,
    short_name text
);

create table if not exists student(
    id int not null primary key,
    surname text,
    name text,
    second_name text,
    study_group_id int,
    foreign key (study_group_id) references study_group(id) on delete set null on update cascade
);

create table if not exists exam_type(
    id int not null primary key,
    type text
);

create table if not exists study_plan(
    id int not null primary key,
    subject_id int,
    exam_type_id int,
    foreign key (subject_id) references subject(id) on delete set null on update cascade,
    foreign key (exam_type_id) references exam_type(id) on delete set null on update cascade
);

create table if not exists mark(
    id int not null primary key,
    name text,
    value text
);

create table if not exists journal(
    id int not null primary key,
    student_id int,
    study_plan_id int,
    in_time bit,
    count int,
    mark_id int,
    foreign key (student_id) references student(id) on delete set null on update cascade,
    foreign key (study_plan_id) references study_plan(id) on delete set null on update cascade,
    foreign key (mark_id) references mark(id) on delete set null on update cascade
);
