package mixpowder.newmusicbot.commands;

import mixpowder.newmusicbot.youtube.YTDLP;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DownloadCommand extends Command{

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		event.getChannel().asTextChannel().sendMessage(YTDLP.getDirectUrl(args[1])).queue();
	}




}
