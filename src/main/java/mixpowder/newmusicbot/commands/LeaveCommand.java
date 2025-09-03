package mixpowder.newmusicbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class LeaveCommand extends Command {

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		if(this.start) {
			this.leaveVoiceChannel();
		}else {
			event.getChannel().asTextChannel().sendMessage("まずはスタートしてね").queue();
		}
	}

}
