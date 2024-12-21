
create table if not exists directions
(
    id BIGSERIAL,
    name VARCHAR,
    CONSTRAINT pk_direction_id primary key (id)
);

create table if not exists years
(
    id BIGSERIAL,
    year INT,
    CONSTRAINT pk_year_id primary key (id)
);

create table if not exists university_groups
(
    id BIGSERIAL,
    number INT,
    user_count INT,
    direction_id BIGINT,
    year_id BIGINT,
    CONSTRAINT pk_university_groups_id primary key (id),
    CONSTRAINT fk_direction_id  FOREIGN KEY (direction_id) REFERENCES directions (id) ON DELETE CASCADE,
    CONSTRAINT fk_year_id  FOREIGN KEY (year_id) REFERENCES years (id) ON DELETE CASCADE
);


create table if not exists users
(
    id BIGSERIAL,
    email VARCHAR,
    firstname VARCHAR,
    surname VARCHAR,
    patronymic VARCHAR,
    password VARCHAR,
    university_group_id BIGINT,
    CONSTRAINT pk_users primary key (id),
    CONSTRAINT fk_group_id  FOREIGN KEY (university_group_id) REFERENCES university_groups (id) ON DELETE CASCADE
);


create table if not exists corpus
(
    id BIGSERIAL,
    town VARCHAR,
    street VARCHAR,
    house VARCHAR,
    CONSTRAINT pk_corpus_id primary key (id)
);

create table if not exists auditory
(
    id BIGSERIAL,
    number INT,
    capacity INT,
    corpus_id BIGINT,
    CONSTRAINT pk_auditory_id primary key (id),
    CONSTRAINT fk_corpus_id FOREIGN KEY (corpus_id) REFERENCES corpus (id) ON DELETE CASCADE
);

create table if not exists event
(
    id BIGSERIAL,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    user_count INT,
    description VARCHAR,
    auditory_id BIGINT,
    user_id BIGINT,
    CONSTRAINT pk_event_id primary key (id),
    CONSTRAINT fk_auditory_id FOREIGN KEY (auditory_id) REFERENCES auditory (id) ON DELETE CASCADE
);

