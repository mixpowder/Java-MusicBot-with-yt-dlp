package mixpowder.newmusicbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SkipCommand extends Command {

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		if(Command.start) {
			Command.musicManager.scheduler.nextTrack();
		}
	}

}
