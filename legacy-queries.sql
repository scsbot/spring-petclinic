-- canary: unrelated line, must survive untouched
SELECT * FROM events WHERE created_at > NOW() - INTERVAL '2 days ago';
-- TODO [PG17.10-adminpack]: adminpack removed - drop this statement
CREATE EXTENSION adminpack;
-- TODO [PG17.10-blkread]: renamed to shared_blk_read_time in pg_stat_statements only - pg_stat_database keeps blk_read_time unchanged, check which view this queries
-- TODO [PG17.10-blkwrite]: renamed to shared_blk_write_time in pg_stat_statements only - pg_stat_database keeps blk_write_time unchanged, check which view this queries
SELECT blk_read_time, blk_write_time FROM pg_stat_database;
-- TODO [PG17.10-buffersbackend]: buffers_backend/buffers_backend_fsync removed - use pg_stat_io or pg_stat_checkpointer
SELECT buffers_backend, buffers_backend_fsync FROM pg_stat_bgwriter;
-- TODO [PG17.10-maxdeadtuples]: renamed to max_dead_tuple_bytes AND changed from a tuple count to a byte figure - do not rename in place, the value's units changed
-- TODO [PG17.10-numdeadtuples]: renamed to num_dead_item_ids - PG17 changed how dead tuples are counted (indexed item IDs, not tuples) - do not rename in place, the value's semantics changed
UPDATE pg_stat_progress_vacuum SET max_dead_tuples = 100, num_dead_tuples = 5;
SELECT colllocale, datlocale FROM pg_collation;
