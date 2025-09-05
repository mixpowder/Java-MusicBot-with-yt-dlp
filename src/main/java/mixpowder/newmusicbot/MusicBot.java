package mixpowder.newmusicbot;
import java.util.HashMap;
import java.util.Map;

import mixpowder.newmusicbot.commands.Command;
import mixpowder.newmusicbot.commands.DownloadCommand;
import mixpowder.newmusicbot.commands.EqualizerCommand;
import mixpowder.newmusicbot.commands.LeaveCommand;
import mixpowder.newmusicbot.commands.PlayCommand;
import mixpowder.newmusicbot.commands.SkipCommand;
import mixpowder.newmusicbot.commands.StopCommand;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class MusicBot extends ListenerAdapter {


	private Map<String, Command> commands = new HashMap<>();

	private String token = "";

	public MusicBot() {

		JDABuilder.createDefault(token)
				.enableIntents(GatewayIntent.GUILD_VOICE_STATES)
				.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
				.enableCache(CacheFlag.VOICE_STATE)
				.addEventListeners(this)
				.build();



		commands.put("!play", new PlayCommand());
		commands.put("!skip", new SkipCommand());
		commands.put("!stop", new StopCommand());
		commands.put("!download", new DownloadCommand());
		commands.put("!eq", new EqualizerCommand());
		commands.put("!leave", new LeaveCommand());
	}

	public static void main(String[] args) {
		new MusicBot();
	}


	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot()) return;
		String[] name = event.getMessage().getContentRaw().split(" ");

		if(commands.containsKey(name[0])) {
			commands.get(name[0]).execute(event, name);
		}



	}


}