CREATE TABLE IF NOT EXISTS exchange_rates (
    id BIGSERIAL  PRIMARY KEY,
    num_code     VARCHAR(10),
    char_code     VARCHAR(10),
    date         TIMESTAMP(6),
    value      NUMERIC(38, 5)
);