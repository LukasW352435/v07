import studiplayer.basic.BasicPlayer;
import studiplayer.basic.TagReader;
import java.io.File;
import java.util.Map;
import javax.naming.ldap.BasicControl;


public class TaggedFile extends AudioFile {
    Map<String,Object> tag_map;
    public TaggedFile(){
        super();
    }

    public TaggedFile(String s){
        super(s);
    }

    public static String timeFormatter(long microtime){
        if(microtime<0){
            throw new RuntimeException("Negativ time value provided");
        }else if(microtime>(long)6*Math.pow(10,9)){
            throw new RuntimeException("Time value exceeds allowed format");
        }else {
            return String.format("%02d:%02d", (int) (microtime / Math.pow(10, 6) / 60), (int) (microtime / Math.pow(10, 6) % 60));
        }
    }

    public void readAndStoreTags(String pathname){
        this.tag_map = TagReader.readTags(pathname);

        for(String key: tag_map.keySet()) {
            if(key.equals("author")) {
                this.setAuthor((String)this.tag_map.get(key));
            }
        }
    }

    @Override
    public void play() {
        BasicPlayer.play(getPathname());
    }

    @Override
    public void togglePause() {
        BasicPlayer.togglePause();
    }

    @Override
    public void stop() {
        BasicPlayer.stop();
    }

    @Override
    public String getFormattedDuration() {
        return "";
    }

    @Override
    public String getFormattedPosition() {
        return "";
    }
}
