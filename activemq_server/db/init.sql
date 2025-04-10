CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255),
                      email VARCHAR(255)
);

INSERT INTO user (name, email) VALUES ('John Doe', 'john.doe@example.com');
