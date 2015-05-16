package nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities;

/**
 * I added some variables to this. Just an example, but the functionality should be there.
 *
 * How about
 * String excuse - The excuse
 * char sex - Excuse most probably used by femal/male/both
 * int minAge - Minimum age for who would probably use this excuse
 * int maxAge - Maximum age for who would probably use this excuse
 * String usedOn - Who this excuse could be used on (E.g: Teacher, Employer, Friend, Lover....?)
 *
 * Some other variables could be a String storing who this excuse actually have been used on and
 * the date that it was last used on such a person. Could also have a rating for what kind of excuse
 * this is (Bullet proof/Random/Obvious).
 *
 * Created by Oeyvind on 14.05.2015.
 */
public class Excuse {

    private int id;
    private String excuse;
    private char sex;
    private int minAge;
    private int maxAge;
    private String usedOn;

    public Excuse(int id, String excuse, char sex, int minAge, int maxAge, String usedOn) {
        this.id = id;
        this.excuse = excuse;
        this.sex = sex;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.usedOn = usedOn;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
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

    public String getUsedOn() {
        return usedOn;
    }

    public void setUsedOn(String usedOn) {
        this.usedOn = usedOn;
    }



}
