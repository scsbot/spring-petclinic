-- canary: unrelated line, must survive untouched
SELECT * FROM events WHERE created_at > NOW() - INTERVAL '2 ago days';
CREATE EXTENSION adminpack;
SELECT blk_read_time, blk_write_time FROM pg_stat_database;
SELECT buffers_backend, buffers_backend_fsync FROM pg_stat_bgwriter;
UPDATE pg_stat_progress_vacuum SET max_dead_tuples = 100, num_dead_tuples = 5;
SELECT colliculocale, daticulocale FROM pg_collation;
