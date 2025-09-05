package mixpowder.newmusicbot.commands;

import mixpowder.newmusicbot.handler.LoadResultHandler;
import mixpowder.newmusicbot.youtube.YTDLP;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PlayCommand extends Command{


	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		final String url = YTDLP.getDirectUrl(args[1]);
		final TextChannel textChannel = event.getChannel().asTextChannel();
		Guild guild = event.getGuild();
		VoiceChannel voiceChannel = event.getMember().getVoiceState().getChannel().asVoiceChannel();

		if (voiceChannel == null) {

			textChannel.sendMessage("ボイスチャットに入ってから再生してね").queue();
			return;
		}


		if (!Command.start) {
			setAudioSource();
			joinVoiceChannel(guild, voiceChannel);
			Command.start = true;

		}

		playerManager.loadItem(url, new LoadResultHandler(musicManager, textChannel));

	}




}
