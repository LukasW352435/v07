import javazoom.jl.converter.WaveFile;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Test;
import studiplayer.basic.TagReader;

import java.util.Map;

import static org.junit.Assert.*;


public class UTestTaggedFile {
   @Ignore
    public void test_play_01() throws Exception{
       TaggedFile tf = new TaggedFile("audiofiles/Rock 812.mp3");
       tf.play();

   }
    @Ignore
    public void test_play_02() throws Exception{
        TaggedFile tf = new TaggedFile("audiofiles/beethoven-ohne-album.mp3");
        while(true)
        tf.play();
    }
    @Test
    public void test_timeFormatter_07() throws Exception{
        try {
            // Call method with time value that underflows our format
            assertEquals("Wrong Time","99:59",TaggedFile.timeFormatter(5999999999L));
        }catch (RuntimeException e){
            // Expected
        }
    }
    @Test
    public void test_timeFormatter_08() throws Exception{
        try {
            // Call method with time value that underflows our format
            TaggedFile.timeFormatter(-1L);
            fail("Time value underflows format; expecting exception");
        }catch (RuntimeException e){
            // Expected
        }
    }
    @Test
    public void test_timeFormatter_09() throws Exception{
        try {
            // Call method with time value that overflows our format
            TaggedFile.timeFormatter(6000000000L);
            fail("Time value overflows format; expecting exception");
        }catch (RuntimeException e){
            // Expected
        }
    }
    @Test
    public void test_timeFormatter_10() throws Exception{
       assertEquals("Wrong time format","05:05",TaggedFile.timeFormatter(305862000L));
    }
    // Read all tags from a TaggedFile
    // This test demonstrates the functionality of TagReader.readTags()
    @Ignore
    public void test_readTags_01() throws Exception{
       TaggedFile tf = new TaggedFile("audiofiles/Rock 812.mp3");
       Map<String,Object> tag_map = TagReader.readTags(tf.getPathname());
       for(String key: tag_map.keySet()){
           System.out.printf("\nKey: %s\n",key);
           System.out.printf("Type of value: %s\n",tag_map.get(key).getClass().toString());
           System.out.println("Value: "+tag_map.get(key));
       }
    }
    @Ignore
    public void test_readTags_02() throws Exception{
        TaggedFile tf = new TaggedFile("audiofiles/Rock 812.mp3");
        tf.readAndStoreTags(tf.getPathname());
        System.out.println();
        System.out.println(tf.getAuthor());
    }
    @Test
    public void test_readAndStoreTags_03() throws Exception{
       TaggedFile tf = new TaggedFile();
       tf.readAndStoreTags("audiofiles/Rock 812.mp3");
       assertEquals("Wrong author","Eisbach",tf.getAuthor());
       assertEquals("Wrong title","Rock 812",tf.getTitle());
       assertEquals("Wrong album","The Sea, the Sky",tf.getAlbum());
       assertEquals("Wrong time format","05:31",tf.getFormattedDuration());
    }
    @Test
    public void text_computeDuration_02() throws Exception{
       assertEquals("Wrong duration",2000000L,WavFile.computeDuration(88200L,44100.0f));
    }
    @Test
    public void test_readAndSetDurationFromFile_01() throws Exception{
       WavFile wf = new WavFile();
       wf.parsePathname("audiofiles/wellenmeister - tranquility.wav");
       wf.readAndSetDurationFromFile(wf.getPathname());
       assertEquals("Wrong time format","02:21",wf.getFormattedDuration());
    }


}