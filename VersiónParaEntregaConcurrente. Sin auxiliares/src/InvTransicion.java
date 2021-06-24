import java.util.ArrayList;
import java.util.List;

public class InvTransicion {

    private String name;
    private int[] grupos;
    private List<Character> group = new ArrayList<Character>();


    public InvTransicion (String name, int[] grupos) {
        this.name = name;
        this.grupos = grupos;

        for (int i = 0; i < grupos.length; i++) {
            group.add(grupos.toString().charAt(i));
        }
    }

    public String getName () {
        return name;
    }

    public int[] getGrupos () {
        return grupos;
    }

    public List getGroup() {
        return group;
    }

}