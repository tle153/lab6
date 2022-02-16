import java.util.Comparator;

public class ComposedComparator implements Comparator<Song>
{
    Comparator<Song> comparing1;
    Comparator<Song> comparing2;

    ComposedComparator(Comparator<Song> o1, Comparator<Song> o2)
    {
        comparing1 = o1;
        comparing2 = o2;
    };

    @Override
    public int compare(Song o1, Song o2) {
        int val = comparing1.compare(o1, o2);
        if(val!=0) {
            return comparing2.compare(o1, o2);
        }

        return 0;
    }
}
