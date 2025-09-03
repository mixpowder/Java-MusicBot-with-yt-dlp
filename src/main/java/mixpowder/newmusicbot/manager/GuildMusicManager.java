package mixpowder.newmusicbot.manager;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

import mixpowder.newmusicbot.handler.AudioPlayerSendHandler;
import mixpowder.newmusicbot.scheduler.TrackScheduler;


public class GuildMusicManager {

	public final AudioPlayer player;

	public final TrackScheduler scheduler;

	public GuildMusicManager(AudioPlayerManager manager) {
		player = manager.createPlayer();
		scheduler = new TrackScheduler(player);
		player.addListener(scheduler);
	}

	public AudioPlayerSendHandler getSendHandler() {
		return new AudioPlayerSendHandler(player);
	}
}