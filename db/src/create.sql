CREATE TABLE users (
    user_id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL ,
    email VARCHAR(50) NOT NULL ,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TYPE KIND AS ENUM ('dog', 'cat', 'others later');

create type purpose_type as enum ('procreation', 'walking');

CREATE TABLE pet_profile (
    pet_profile_id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT NOT NULL ,
    purpose PURPOSE_TYPE,

    PRIMARY KEY (pet_profile_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE passport(
    passport_id INT GENERATED ALWAYS AS IDENTITY,
    pet_profile_id INT NOT NULL,
    name VARCHAR(55) NOT NULL ,
    birth_date DATE NOT NULL ,
    kind KIND,
    breed VARCHAR(55) NOT NULL,
    breeding_certificate BOOLEAN NOT NULL,
    coat VARCHAR(55) NOT NULL,
    bio VARCHAR(255),

    PRIMARY KEY (passport_id),
    CONSTRAINT fk_pet_profile FOREIGN KEY (pet_profile_id)
                     REFERENCES pet_profile(pet_profile_id)
);


CREATE TABLE photos(
    photo_id INT GENERATED ALWAYS AS IDENTITY ,
    pet_profile_id INT NOT NULL,
    file_link INT NOT NULL ,

    PRIMARY KEY (photo_id),
    CONSTRAINT fk_pet_profile FOREIGN KEY (pet_profile_id)
                   REFERENCES pet_profile(pet_profile_id)
);

CREATE TABLE tags(
    tag_id INT GENERATED ALWAYS AS IDENTITY ,
    name VARCHAR(50) NOT NULL ,

    PRIMARY KEY (tag_id)
);

CREATE TABLE pet_xref_tag (
    pet_profile_id INT NOT NULL,
    tag_id INT NOT NULL,

    CONSTRAINT fk_pet_profile FOREIGN KEY (pet_profile_id)
                   REFERENCES pet_profile(pet_profile_id),
    CONSTRAINT fk_tag FOREIGN KEY (tag_id)
                    REFERENCES tags(tag_id)
);

CREATE TABLE likes(
    like_id INT GENERATED ALWAYS AS IDENTITY ,
    object_pet_id INT NOT NULL ,
    target_pet_id INT NOT NULL ,
    date DATE NOT NULL DEFAULT current_date,

    PRIMARY KEY (like_id),
    CONSTRAINT fk_object_pet FOREIGN KEY (object_pet_id)
                  REFERENCES pet_profile(pet_profile_id),
    CONSTRAINT fk_target_pet FOREIGN KEY (target_pet_id)
                  REFERENCES pet_profile(pet_profile_id)
);

CREATE TYPE SOCIAL_MEDIA_TYPE AS ENUM ('twitter', 'instagram', 'telegram');

CREATE TABLE social_media(
    social_media_id INT GENERATED ALWAYS AS IDENTITY ,
    pet_profile INT NOT NULL ,
    type SOCIAL_MEDIA_TYPE,
    link VARCHAR(50) NOT NULL,

    PRIMARY KEY (social_media_id),
    CONSTRAINT fk_pet_profile FOREIGN KEY (pet_profile)
                         REFERENCES pet_profile(pet_profile_id)
);


CREATE TABLE viewed_profiles(
    viewed_profile_id INT GENERATED ALWAYS AS IDENTITY ,
    object_pet_id INT NOT NULL ,
    target_pet_id INT NOT NULL ,

    PRIMARY KEY (viewed_profile_id),
    CONSTRAINT fk_object_pet FOREIGN KEY (object_pet_id)
                  REFERENCES pet_profile(pet_profile_id),
    CONSTRAINT fk_target_pet FOREIGN KEY (target_pet_id)
                  REFERENCES pet_profile(pet_profile_id)
);

CREATE TABLE match (
    match_id INT GENERATED ALWAYS AS IDENTITY ,
    first_pet_id INT NOT NULL ,
    second_pet_id INT NOT NULL ,

    PRIMARY KEY (match_id),
    CONSTRAINT fk_first_pet FOREIGN KEY (first_pet_id)
                  REFERENCES pet_profile(pet_profile_id),
    CONSTRAINT fk_second_oet FOREIGN KEY (second_pet_id)
                  REFERENCES pet_profile(pet_profile_id)
);
