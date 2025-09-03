package mixpowder.newmusicbot.handler;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import mixpowder.newmusicbot.manager.GuildMusicManager;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class LoadResultHandler implements AudioLoadResultHandler{

	private GuildMusicManager musicManager;
	private TextChannel textChannel;

	public LoadResultHandler(GuildMusicManager musicManager, TextChannel textChannel) {
		this.musicManager = musicManager;
		this.textChannel = textChannel;
	}

	@Override
	public void trackLoaded(AudioTrack track) {
		musicManager.scheduler.queue(track);
		textChannel.sendMessage("キューに曲追加できたよ！").queue();
	}

	@Override
	public void playlistLoaded(AudioPlaylist playlist) {
	}

	@Override
	public void noMatches() {
		textChannel.sendMessage("曲が見つからなかったよ").queue();
	}

	@Override
	public void loadFailed(FriendlyException exception) {
		textChannel.sendMessage("なんか知らないけどロードできなかったよ～～").queue();
	}

}
