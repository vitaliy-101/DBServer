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

CREATE OR REPLACE PROCEDURE delete_auditory_by_number(auditory_number INT)
    LANGUAGE plpgsql AS $$
BEGIN
    DELETE FROM auditory WHERE number = auditory_number;
END;
$$;

CREATE OR REPLACE PROCEDURE check_user_count(auditoryId INT, userCount BIGINT, OUT result BOOLEAN)
    LANGUAGE plpgsql AS $$
BEGIN
    SELECT COUNT(*) > 0 INTO result
    FROM auditory
    WHERE auditory.id = auditoryId AND auditory.capacity >= userCount;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_direction_by_name(direction_name VARCHAR)
    LANGUAGE plpgsql AS $$
BEGIN
    DELETE FROM directions
    WHERE name = direction_name;
END;
$$;

CREATE OR REPLACE PROCEDURE find_direction_by_name(direction_name VARCHAR, OUT direction_id BIGINT, OUT direction_name_out VARCHAR)
    LANGUAGE plpgsql AS $$
BEGIN
    SELECT id, name
    INTO direction_id, direction_name_out
    FROM directions
    WHERE name = direction_name
    LIMIT 1;
END;
$$;

CREATE OR REPLACE PROCEDURE check_event_time_overlap(
    start_time_input TIMESTAMP,
    end_time_input TIMESTAMP,
    auditory_id_input BIGINT,
    OUT event_exists BOOLEAN
)
    LANGUAGE plpgsql AS $$
BEGIN
    -- Проверяем, если существует хотя бы одно событие, которое пересекается с переданным временем и аудиторией
    SELECT COUNT(*) > 0
    INTO event_exists
    FROM event ev
    WHERE (ev.start_time <= start_time_input AND start_time_input <= ev.end_time)
       OR (end_time_input >= ev.start_time AND end_time_input <= ev.end_time)
        AND ev.auditory_id = auditory_id_input;
END;
$$;
CREATE OR REPLACE PROCEDURE delete_group_by_number(
    number_input INT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM university_groups WHERE number = number_input;
END;
$$;

CREATE OR REPLACE PROCEDURE find_group_by_number(
    number_input INT,
    OUT group_id_output BIGINT,
    OUT group_number_output INT,
    OUT user_count_output INT,
    OUT direction_id_output BIGINT,
    OUT year_id_output BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    SELECT university_groups.id, university_groups.number, university_groups.user_count, university_groups.direction_id, university_groups.year_id
    INTO group_id_output, group_number_output, user_count_output, direction_id_output, year_id_output
    FROM university_groups
    WHERE number = number_input
    LIMIT 1;
END;
$$;

CREATE OR REPLACE PROCEDURE increase_user_count(
    id_input BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE university_groups
    SET user_count = user_count + 1
    WHERE id = id_input;
END;
$$;

CREATE OR REPLACE PROCEDURE decrease_user_count(
    id_input BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE university_groups
    SET user_count = user_count - 1
    WHERE id = id_input;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_user_by_email(
    email_input VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM users WHERE email = email_input;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_user_by_firstname(
    firstname_input VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM users WHERE firstname = firstname_input;
END;
$$;
CREATE OR REPLACE PROCEDURE delete_user_by_surname(
    surname_input VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM users WHERE surname = surname_input;
END;
$$;
CREATE OR REPLACE PROCEDURE delete_user_by_patronymic(
    patronymic_input VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM users WHERE patronymic = patronymic_input;
END;
$$;
CREATE OR REPLACE PROCEDURE find_user_by_email(
    email_input VARCHAR,
    OUT user_id_output BIGINT,
    OUT email_output VARCHAR,
    OUT firstname_output VARCHAR,
    OUT surname_output VARCHAR,
    OUT patronymic_output VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    SELECT id, email, firstname, surname, patronymic
    INTO user_id_output, email_output, firstname_output, surname_output, patronymic_output
    FROM users
    WHERE email = email_input
    LIMIT 1;
END;
$$;

CREATE OR REPLACE PROCEDURE find_user_by_email(
    email_input VARCHAR,
    OUT user_id_output BIGINT,
    OUT email_output VARCHAR,
    OUT firstname_output VARCHAR,
    OUT surname_output VARCHAR,
    OUT patronymic_output VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    SELECT id, email, firstname, surname, patronymic
    INTO user_id_output, email_output, firstname_output, surname_output, patronymic_output
    FROM users
    WHERE email = email_input
    LIMIT 1;
END;
$$;

CREATE OR REPLACE PROCEDURE find_user_by_firstname(
    firstname_input VARCHAR,
    OUT user_id_output  BIGINT,
    OUT email_output  VARCHAR,
    OUT firstname_output  VARCHAR,
    OUT surname_output  VARCHAR,
    OUT patronymic_output  VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    SELECT id, email, firstname, surname, patronymic
    INTO user_id_output , email_output , firstname_output , surname_output , patronymic_output
    FROM users
    WHERE firstname = firstname_input
    LIMIT 1;
END;
$$;

CREATE OR REPLACE PROCEDURE find_user_by_surname(
    surname_input VARCHAR,
    OUT user_id_output BIGINT,
    OUT email_output VARCHAR,
    OUT firstname_output VARCHAR,
    OUT surname_output VARCHAR,
    OUT patronymic_output VARCHAR
)
    LANGUAGE plpgsql
AS $$
BEGIN
    SELECT id, email, firstname, surname, patronymic
    INTO user_id_output, email_output, firstname_output, surname_output, patronymic_output
    FROM users
    WHERE surname = surname_input
    LIMIT 1;
END;
$$;



CREATE OR REPLACE PROCEDURE exists_user_by_password_and_email(
    password_input VARCHAR,
    email_input VARCHAR,
    OUT exists_output BOOLEAN
)
    LANGUAGE plpgsql
AS $$
BEGIN
    SELECT EXISTS (
        SELECT 1 FROM users
        WHERE email = email_input AND password = password_input
    ) INTO exists_output;
END;
$$;

CREATE OR REPLACE PROCEDURE delete_year_by_year(
    year_input INT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM years WHERE year = year_input;
END;
$$;

CREATE OR REPLACE PROCEDURE find_year_by_year(
    year_input INT,
    OUT year_id_output BIGINT,
    OUT year_output INT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    SELECT id, year
    INTO year_id_output, year_output
    FROM years
    WHERE year = year_input
    LIMIT 1;
END;
$$;

CREATE OR REPLACE FUNCTION update_user_count_on_insert()
    RETURNS TRIGGER AS $$
BEGIN

    UPDATE university_groups
    SET user_count = user_count + 1
    WHERE id = NEW.university_group_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_user_count
    AFTER INSERT ON users
    FOR EACH ROW
EXECUTE FUNCTION update_user_count_on_insert();

CREATE OR REPLACE FUNCTION update_user_count_on_delete()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE university_groups
    SET user_count = user_count - 1
    WHERE id = OLD.university_group_id;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_user_count_on_delete
    AFTER DELETE ON users
    FOR EACH ROW
EXECUTE FUNCTION update_user_count_on_delete();
