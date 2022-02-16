import java.util.Comparator;

public class ArtistComparator implements Comparator<Song>
{

    @Override
    public int compare(Song o1, Song o2) {
        String artist1 = o1.getArtist();
        String artist2 = o2.getArtist();
        int length = artist1.length() > artist2.length() ? artist1.length() : artist2.length();
        for(int i = 0; i < length; i++) {
            int comparison = artist1.compareTo(artist2);
            if(comparison != 0) {
                return comparison;
            }
        }
        return 0;
    }
}
