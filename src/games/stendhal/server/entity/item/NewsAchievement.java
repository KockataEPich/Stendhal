package games.stendhal.server.entity.item;

import java.time.LocalDateTime;

public class NewsAchievement implements Comparable<NewsAchievement> {
	private String title;
	private String playerName;
	private LocalDateTime timestamp;
	private int score;

	public NewsAchievement(String title, String playerName, LocalDateTime timestamp, Integer score) {
		this.title = title;
		this.playerName = playerName;
		this.timestamp = timestamp;
		this.score = score;
	}

	public String toString() {
		return String.format("%s completed the '%s' achievement", this.playerName, this.title);
	}

	public String getTitle() {
		return this.title;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	public int getScore() {
		return this.score;
	}

	@Override
	public int compareTo(NewsAchievement other) {
		return this.score - other.score;

	}
}
