-- ===========================
-- Tabela Category
-- ===========================
CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- ===========================
-- Tabela Streaming
-- ===========================
CREATE TABLE streaming (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- ===========================
-- Tabela Movie
-- ===========================
CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    release_date DATE,
    rating NUMERIC,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- ===========================
-- Tabela Movie_Category (Relacionamento N-N)
-- ===========================
CREATE TABLE movie_category (
    movie_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    CONSTRAINT fk_movie_category_movie
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    CONSTRAINT fk_movie_category_category
    FOREIGN KEY (category_id) REFERENCES category(id)
);

-- ===========================
-- Tabela Movie_Streaming (Relacionamento N-N)
-- ===========================
CREATE TABLE movie_streaming (
    movie_id INTEGER NOT NULL,
    streaming_id INTEGER NOT NULL,
    CONSTRAINT fk_movie_streaming_movie
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    CONSTRAINT fk_movie_streaming_streaming
    FOREIGN KEY (streaming_id) REFERENCES streaming(id)
);

-- ===========================
-- Tabela Users
-- ===========================
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
