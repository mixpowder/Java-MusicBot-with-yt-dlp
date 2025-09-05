package mixpowder.newmusicbot.commands;

import com.sedmelluq.discord.lavaplayer.filter.equalizer.EqualizerFactory;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import mixpowder.newmusicbot.manager.GuildMusicManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

	protected static AudioPlayerManager playerManager;
	protected static GuildMusicManager musicManager;
	protected static boolean start = false;

	protected static EqualizerFactory eqSettings;

	private static Guild joiningVoiceChannel;



	public abstract void execute(MessageReceivedEvent event, String[] args);

	protected void joinVoiceChannel(Guild guild, VoiceChannel voiceChannel) {
		Command.joiningVoiceChannel = guild;
		guild.getAudioManager().openAudioConnection(voiceChannel);
		Command.musicManager = new GuildMusicManager(playerManager);
		Command.musicManager.player.setVolume(5);

		eqSettings = new EqualizerFactory();

		guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
	}

	protected static void leaveVoiceChannel() {
		Command.joiningVoiceChannel.getAudioManager().closeAudioConnection();
	}

	protected void setAudioSource() {
		Command.playerManager = new DefaultAudioPlayerManager();
		playerManager.getConfiguration().setFilterHotSwapEnabled(true);
		AudioSourceManagers.registerRemoteSources(playerManager);
		AudioSourceManagers.registerLocalSource(playerManager);

	}

}
