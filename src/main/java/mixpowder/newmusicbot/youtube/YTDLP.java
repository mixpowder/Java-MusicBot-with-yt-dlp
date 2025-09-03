package mixpowder.newmusicbot.youtube;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YTDLP {

	public static String getDirectUrl(String youtubeUrl) {
		String directUrl = null;
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(
					"tools\\yt-dlp", "-g", "-f bestaudio[ext=m4a]", youtubeUrl

					);
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			directUrl = reader.readLine();
			reader.close();
			//process.waitFor();


		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();

        }

		return directUrl;
    }
}
