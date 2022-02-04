public class Rates {
    private int dateOfBirth;
    private int universityLocation;
    private int field;
    private int workplace;
    private int specialties;
    private int degree;

    public Rates() {
        this(1, 350, 450, 550, 1000, 100);
    }

    public Rates(int dateOfBirth, int universityLocation, int field, int workplace, int specialties, int degree) {
        this.dateOfBirth = dateOfBirth;
        this.universityLocation = universityLocation;
        this.field = field;
        this.workplace = workplace;
        this.specialties = specialties;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Rates{" +
                "dateOfBirth=" + dateOfBirth +
                ", universityLocation=" + universityLocation +
                ", field=" + field +
                ", workplace=" + workplace +
                ", specialties=" + specialties +
                ", degree=" + degree +
                '}';
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

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
