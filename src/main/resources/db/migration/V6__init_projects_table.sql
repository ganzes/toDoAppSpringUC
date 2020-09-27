drop table if exists projects;
drop table if exists projects_steps;

create table projects (
    id int primary key auto_increment,
    description varchar (100) not null
    );
alter table task_groups add column project_id int null;
alter table task_groups add foreign key (project_id) references projects (id);

create table projects_steps (
    id int primary key auto_increment,
    description varchar (100) not null,
    days_to_deadline int not null
);
alter table projects_steps add column project_id int null;
alter table projects_steps add foreign key (project_id) references projects (id);