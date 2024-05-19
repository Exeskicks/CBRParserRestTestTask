CREATE TABLE IF NOT EXISTS exchange_rates (
    id BIGSERIAL  PRIMARY KEY,
    num_code     VARCHAR(10),
    char_code     VARCHAR(10),
    date         DATE,
    value      NUMERIC(38, 5)
);