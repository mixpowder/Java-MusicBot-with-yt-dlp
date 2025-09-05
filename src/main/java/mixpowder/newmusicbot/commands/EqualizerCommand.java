package mixpowder.newmusicbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class EqualizerCommand extends Command {

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		if(Command.start) {
			if(args.length == 2){
				event.getChannel().asTextChannel().sendMessage("現在の設定: " + this.presets(args[1])).queue();
			}else {
				event.getChannel().asTextChannel().sendMessage("引数がおかしいです").queue();
			}

			Command.musicManager.player.setFilterFactory(Command.eqSettings);
		}else {
			event.getChannel().asTextChannel().sendMessage("まずはスタートしてね").queue();
		}
	}

	private String presets(String name) {
		String presets = "";

		if(name.equals("bass")) {

			for (int i = 0; i < 15; i++) {

			    float distance = Math.abs(i - 7);
			    float t = distance / 7.0f;
			    float gain = -0.1f + (0.1f - (-0.1f)) * t;
			    Command.eqSettings.setGain(i, gain);

			}
			presets = "bass";

		}else if(name.equals("normal")) {

			for (int i = 0; i < 15; i++) {
			    Command.eqSettings.setGain(i, 0.0f);
			}
			presets = "flat";

		}else if(name.equals("reble")) {

			for (int i = 0; i < 15; i++) {
				float distance = Math.abs(i - 7);
				float t = (1.0f - (distance / 7.0f));
				float gain = -0.1f + (0.1f - (-0.1f)) * t;
			    Command.eqSettings.setGain(i, gain);
			}

			presets = "vocal";

		}

		return presets;
	}

}
