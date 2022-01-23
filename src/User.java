import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class User {

    // General Info
    private String id;
    private String name;
    private String dateOfBirth;
    // Special Info
    private String universityLocation;
    private String field;
    private String workplace;
    // Specialties
    private HashSet<String> specialties;
    // Connections
    private HashSet<String> connectionId;

    // score rate of each property
    private Rates rates;

    public User() {
        rates = new Rates();
    }

    public User(String id, String name, String dateOfBirth, String universityLocation, String field, String workplace, HashSet<String> specialties) {
        this();
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.universityLocation = universityLocation;
        this.field = field;
        this.workplace = workplace;
        this.specialties = specialties;
        this.connectionId = new HashSet<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", universityLocation='" + universityLocation + '\'' +
                ", field='" + field + '\'' +
                ", workplace='" + workplace + '\'' +
                ", specialties=" + specialties +
                ", connectionId=" + connectionId +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUniversityLocation() {
        return universityLocation;
    }

    public String getField() {
        return field;
    }

    public String getWorkplace() {
        return workplace;
    }

    public HashSet<String> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(HashSet<String> specialties) {
        this.specialties = specialties;
    }

    public HashSet<String> getConnectionId() {
        return connectionId;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public int score(User user, int degree) {

        int score = rates.getDegree() / degree;

        score += rates.getDateOfBirth() * diffAge(dateOfBirth, user.getDateOfBirth());

        score += universityLocation.equals(user.getUniversityLocation()) ? rates.getUniversityLocation() : 0;

        score += field.equals(user.getField()) ? rates.getField() : 0;

        score += !workplace.equals("-") && workplace.equals(user.getWorkplace()) ? rates.getWorkplace() : 0;

        for (String specialty : specialties)
            score += user.getSpecialties().contains(specialty) ? rates.getSpecialties() : 0;

        return score;
    }

    private int diffAge(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);

        int diff = 0;
        try {
            Date firstDate = sdf.parse(date1);
            Date secondDate = sdf.parse(date2);
            long diffInMillis = Math.abs(secondDate.getTime() - firstDate.getTime());
            diff = (int) TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Math.max(0, 3600 - diff) / 10;
    }


}
