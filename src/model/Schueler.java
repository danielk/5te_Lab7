package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.SelectableDataModel;

/**
 * @author reio
 */

public class Schueler implements Serializable, SelectableDataModel<Schueler> {

    /**
     *
     */
    private static final long serialVersionUID = 1426600537883403679L;
    private int nr;
    private String nachname;
    private String vorname;
    private char geschlecht;

    private static final List<Schueler> klasse;

    static {

        klasse = new ArrayList<Schueler>();

        klasse.add(new Schueler(1, "Ehn", "Wilhelm", 'M'));
        klasse.add(new Schueler(2, "Gruber", "Sarah", 'W'));
        klasse.add(new Schueler(3, "Guthan", "Raphael", 'M'));
        klasse.add(new Schueler(4, "Hamberger", "Sebastian", 'M'));
        klasse.add(new Schueler(5, "Harold", "Sascha", 'M'));
        klasse.add(new Schueler(6, "Kornberger", "J�rgen", 'M'));
        klasse.add(new Schueler(7, "Navratil", "Philipp", 'M'));
        klasse.add(new Schueler(8, "Pfeiffer", "Michael", 'M'));
        klasse.add(new Schueler(9, "Purker", "Angela", 'W'));
        klasse.add(new Schueler(10, "Rasch", "Patrick", 'M'));
        klasse.add(new Schueler(11, "Ringelhahn", "Carina", 'W'));
        klasse.add(new Schueler(12, "Sattler", "Benedikt", 'M'));
        klasse.add(new Schueler(13, "Schirmer", "Kurt", 'M'));
        klasse.add(new Schueler(14, "Schneider", "Florens", 'M'));
        klasse.add(new Schueler(15, "Simmer", "Patrick", 'M'));
        klasse.add(new Schueler(16, "Staudinger", "Patrik", 'M'));
        klasse.add(new Schueler(17, "Tatzreiter", "Oliver", 'M'));
        klasse.add(new Schueler(18, "Tr�scher", "Dominik", 'M'));
    }


    public void addSchuelerToKlasse(Schueler s) {
        s.nr = klasse.get(klasse.size() - 1).getNr() + 1;
        klasse.add(s);
    }

    public Schueler(String nachname, String vorname, char geschlecht) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.geschlecht = geschlecht;
        addSchuelerToKlasse(this);
    }

    private Schueler(int nr, String nachname, String vorname, char geschlecht) {
        this.nr = nr;
        this.nachname = nachname;
        this.vorname = vorname;
        this.geschlecht = geschlecht;
    }

    public static List<Schueler> getKlasse() {
        return klasse;
    }

    public String getNachname() {
        return nachname;
    }

    public int getNr() {
        return nr;
    }

    public String getVorname() {
        return vorname;
    }

    public char getGeschlecht() {
        return geschlecht;
    }

    @Override
    public Schueler getRowData(String rowKey) {
        List<Schueler> schus = (List<Schueler>) getKlasse();

        for (Schueler schu : schus) {
            if ((schu.getNr() + "").equals(rowKey))
                return schu;
        }
        return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return getVorname() + " " + getNachname() + " - " + getNr();
    }

    @Override
    public Object getRowKey(Schueler schueler) {
        return schueler.getNr();
    }

}