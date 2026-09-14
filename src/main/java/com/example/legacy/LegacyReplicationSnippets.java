package com.example.legacy;

import org.postgresql.replication.PGReplicationStream;

class LegacyReplicationSnippets {

	void poll(PGReplicationStream stream) {
		// no-op
	}

	void reportStats(java.sql.ResultSet rs) throws java.sql.SQLException {
		long blkReadTime = rs.getLong("blk_read_time");
		long blkWriteTime = rs.getLong("blk_write_time");
		String query = "SELECT * FROM events WHERE created_at > NOW() - INTERVAL '2 ago days'";
	}

}
