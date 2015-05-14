package nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities;

/**
 * We need to add variables to this. Just an example, but the functionality should be there.
 * Haven't tested it though.
 *
 * How about
 * String excuse
 * char sex
 * int minAge
 * int maxAge
 * String usedOn
 *
 * Created by Oeyvind on 14.05.2015.
 */
public class Excuse {

    private int id;
    private String excuse;
    private String ageRange;

    public Excuse(int id, String excuse, String ageRange) {
        this.id = id;
        this.excuse = excuse;
        this.ageRange = ageRange;
    }

    public int getId() {
        return this.id;
    }

    public String getExcuse() {
        return excuse;
    }

    public void setExcuse(String excuse) {
        this.excuse = excuse;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }



}
