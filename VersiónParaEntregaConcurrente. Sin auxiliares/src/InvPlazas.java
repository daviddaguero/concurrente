public class InvPlazas {

    private RedDePetri rdp;

    public InvPlazas(RedDePetri rdp) {
        this.rdp = rdp;
    }

    public void CheckInvPlazas(){
        int p1 = rdp.getMP0() + rdp.getMP1() + rdp.getMP9() + rdp.getMP17();
        int p2 = rdp.getMP3() + rdp.getMP4();
        int p3 = rdp.getMP5() + rdp.getMP7() + rdp.getMP8();
        int p4 = rdp.getMP11() + rdp.getMP12();
        int p5 = rdp.getMP13() + rdp.getMP14() + rdp.getMP16();

        assert p1 == 1 : String.format("Invariante 1 no cumplido");
        assert p2 == 1 : String.format("Invariante 2 no cumplido");
        assert p3 == 1 : String.format("Invariante 3 no cumplido");
        assert p4 == 1 : String.format("Invariante 4 no cumplido");
        assert p5 == 1 : String.format("Invariante 5 no cumplido");

    }
}
