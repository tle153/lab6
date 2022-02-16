import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCases1
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator()
   {
      ArtistComparator ac = new ArtistComparator();
      assertTrue(ac.compare(songs[0], songs[1])<0);
   }

   @Test
   public void testLambdaTitleComparator()
   {
      Comparator<Song> titleComparator = (s1, s2) -> {
         String title1 = s1.getTitle();
         String title2 = s2.getTitle();
         int length = title1.length() > title2.length() ? title1.length() : title2.length();
         for(int i = 0; i < length; i++) {
            int comparison = title1.compareTo(title2);
            if(comparison != 0) {
               return comparison;
            }
         }
         return 0;
      };

      assertTrue(titleComparator.compare(songs[0], songs[1])>0);
   }

   @Test
   public void testYearExtractorComparator()
   {
      Comparator<Song> yearComparator = Comparator.comparing(Song::getYear).reversed();

      assertTrue(yearComparator.compare(songs[1], songs[2]) > 0);
   }

   @Test
   public void testComposedComparator()
   {
      ArtistComparator ac = new ArtistComparator();
      Comparator<Song> titleComparator = (s1, s2) -> {
         String title1 = s1.getTitle();
         String title2 = s2.getTitle();
         int length = title1.length() > title2.length() ? title1.length() : title2.length();
         for(int i = 0; i < length; i++) {
            int comparison = title1.compareTo(title2);
            if(comparison != 0) {
               return comparison;
            }
         }
         return 0;
      };
      Comparator<Song> yearComparator = Comparator.comparing(Song::getYear).reversed();
      Comparator<Song> composedComparator = new ComposedComparator(ac, yearComparator);
      assertTrue(yearComparator.compare(songs[3], songs[7]) < 0);
   }

   @Test
   public void testThenComparing()
   {
      Comparator<Song> thenComparing = Comparator.comparing(Song::getTitle).thenComparing(Song::getArtist);
      assertTrue(thenComparing.compare(songs[3], songs[5]) > 0);
   }

   @Test
   public void runSort()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );

      Comparator<Song> thenComparing = Comparator.comparing(Song::getArtist).
              thenComparing(Song::getTitle).
              thenComparing(Song::getYear);

      songList.sort(thenComparing);

      assertEquals(songList, expectedList);
   }
}
