--
-- Copyright © 2019-2020 The CETC PHM Authors
--

CREATE EXTENSION IF NOT EXISTS timescaledb CASCADE;

CREATE TABLE IF NOT EXISTS ts_kv_hyper (
    entity_id varchar(31) NOT NULL,
    attribute_key varchar(255) NOT NULL,
    ts bigint NOT NULL,
    bool_v boolean,
    str_v varchar(10000000),
    long_v bigint,
    dbl_v double precision,
    CONSTRAINT ts_kv_hyper_pkey PRIMARY KEY (entity_id, attribute_key, ts)
);

SELECT create_hypertable('ts_kv_hyper', 'ts', chunk_time_interval => 86400000, if_not_exists => true);
