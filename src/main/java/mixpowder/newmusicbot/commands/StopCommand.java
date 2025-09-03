package mixpowder.newmusicbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class StopCommand extends Command {

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		if(event.getJDA().getStatus() != null) {
			event.getJDA().shutdown();
		}

	}

}
