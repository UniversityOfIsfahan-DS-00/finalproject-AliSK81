public class Rates {
    private int dateOfBirth;
    private int universityLocation;
    private int field;
    private int workplace;
    private int specialties;
    private int level;

    public Rates() {
        this(2, 500, 750, 900, 1200, 100);
    }

    public Rates(int dateOfBirth, int universityLocation, int field, int workplace, int specialties, int level) {
        this.dateOfBirth = dateOfBirth;
        this.universityLocation = universityLocation;
        this.field = field;
        this.workplace = workplace;
        this.specialties = specialties;
        this.level = level;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getUniversityLocation() {
        return universityLocation;
    }

    public void setUniversityLocation(int universityLocation) {
        this.universityLocation = universityLocation;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public int getWorkplace() {
        return workplace;
    }

    public void setWorkplace(int workplace) {
        this.workplace = workplace;
    }

    public int getSpecialties() {
        return specialties;
    }

    public void setSpecialties(int specialties) {
        this.specialties = specialties;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
