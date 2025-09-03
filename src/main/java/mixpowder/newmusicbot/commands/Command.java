package mixpowder.newmusicbot.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import mixpowder.newmusicbot.manager.GuildMusicManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

	protected AudioPlayerManager playerManager;
	protected GuildMusicManager musicManager;
	protected boolean start = false;

	private Guild joiningVoiceChannel;

	public abstract void execute(MessageReceivedEvent event, String[] args);

	protected void joinVoiceChannel(Guild guild, VoiceChannel voiceChannel) {
		this.joiningVoiceChannel = guild;
		guild.getAudioManager().openAudioConnection(voiceChannel);
		this.musicManager = new GuildMusicManager(playerManager);
		this.musicManager.player.setVolume(5);
		guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
	}

	protected void leaveVoiceChannel() {
		this.joiningVoiceChannel.getAudioManager().closeAudioConnection();
	}

	protected void setAudioSource() {
		this.playerManager = new DefaultAudioPlayerManager();
		AudioSourceManagers.registerRemoteSources(playerManager);
		AudioSourceManagers.registerLocalSource(playerManager);

	}

}
