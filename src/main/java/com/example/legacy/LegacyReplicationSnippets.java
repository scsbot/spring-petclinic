package com.example.legacy;

// TODO [pgjdbc]: logical replication is broken from 42.7.11 (open issue #4328) - a routine idle timeout kills the stream. Pin 42.7.10 until it is fixed.
import org.postgresql.replication.PGReplicationStream;

class LegacyReplicationSnippets {

	void poll(PGReplicationStream stream) {
		// no-op
	}

	void reportStats(java.sql.ResultSet rs) throws java.sql.SQLException {
		// TODO [PG17.10-blkread]: renamed to shared_blk_read_time in pg_stat_statements only - pg_stat_database keeps blk_read_time unchanged, check which view this queries
		long blkReadTime = rs.getLong("blk_read_time");
		// TODO [PG17.10-blkwrite]: renamed to shared_blk_write_time in pg_stat_statements only - pg_stat_database keeps blk_write_time unchanged, check which view this queries
		long blkWriteTime = rs.getLong("blk_write_time");
		String query = "SELECT * FROM events WHERE created_at > NOW() - INTERVAL '2 days ago'";
	}

}
