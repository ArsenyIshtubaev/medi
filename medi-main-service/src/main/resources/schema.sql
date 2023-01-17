CREATE TABLE IF NOT EXISTS users
(
    user_id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_name VARCHAR(128) NOT NULL,
    email     VARCHAR(128) NOT NULL,
    UNIQUE (email)
);
CREATE TABLE IF NOT EXISTS booking
(
    booking_id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    annotation         VARCHAR(2000)               NOT NULL,
    category_id        BIGINT REFERENCES categories (category_id),
    description        VARCHAR(7000)               NOT NULL,
    event_date         timestamp WITHOUT TIME ZONE NOT NULL,
    created_date       timestamp WITHOUT TIME ZONE NOT NULL,
    published_date     timestamp WITHOUT TIME ZONE,
    location_id        BIGINT REFERENCES location (location_id),
    initiator_id       BIGINT REFERENCES users (user_id),
    paid               boolean                     NOT NULL,
    title              VARCHAR                     NOT NULL,
    participant_limit  int                         NOT NULL,
    request_moderation boolean                     NOT NULL,
    state              VARCHAR(15)                 NOT NULL,
    CONSTRAINT fk_events_to_users FOREIGN KEY (initiator_id) REFERENCES users (user_id),
    CONSTRAINT fk_events_to_categories FOREIGN KEY (category_id) REFERENCES categories (category_id),
    CONSTRAINT fk_events_to_location FOREIGN KEY (location_id) REFERENCES location (location_id)
);