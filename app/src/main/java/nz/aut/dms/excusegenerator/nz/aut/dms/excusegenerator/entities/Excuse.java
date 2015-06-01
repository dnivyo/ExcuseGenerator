package nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities;

/**
 * Excuse entity
 *
 * Created by Oeyvind on 14.05.2015.
 */
public class Excuse {
    private int id;
    private String person;
    private char quality;
    private String excuse;
    private char sex;
    private int minAge;
    private int maxAge;
    private String usedOn;

    public Excuse(int id, String person, char quality, String excuse, char sex, int minAge, int maxAge, String usedOn) {
        this.id = id;
        this.person = person;
        this.quality = quality;
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

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public char getQuality() {
        return quality;
    }

    public void setQuality(char quality) {
        this.quality = quality;
    }


}
