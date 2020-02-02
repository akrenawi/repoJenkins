package SQLConnection;

import java.sql.Time;

public class ScheduleInJSON {


    private int kav, makat, halofa, direction, distanceFromDistinationToOrigin;
    private Time start, end;
    private String origin, distination;
    private float langitude, lattidude;

    public ScheduleInJSON() { }

    public ScheduleInJSON(int kav, int makat, int halofa, int direction, int distanceFromDistinationToOrigin, Time start, Time end, String origin, String distination, float langitude, float lattidude) {
        this.kav = kav;
        this.makat = makat;
        this.halofa = halofa;
        this.direction = direction;
        this.distanceFromDistinationToOrigin = distanceFromDistinationToOrigin;
        this.start = start;
        this.end = end;
        this.origin = origin;
        this.distination = distination;
        this.langitude = langitude;
        this.lattidude = lattidude;
    }


    public int getKav() {
        return kav;
    }

    public void setKav(int kav) {
        this.kav = kav;
    }

    public int getMakat() {
        return makat;
    }

    public void setMakat(int makat) {
        this.makat = makat;
    }

    public int getHalofa() {
        return halofa;
    }

    public void setHalofa(int halofa) {
        this.halofa = halofa;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDistanceFromDistinationToOrigin() {
        return distanceFromDistinationToOrigin;
    }

    public void setDistanceFromDistinationToOrigin(int distanceFromDistinationToOrigin) {
        this.distanceFromDistinationToOrigin = distanceFromDistinationToOrigin;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDistination() {
        return distination;
    }

    public void setDistination(String distination) {
        this.distination = distination;
    }

    public float getLangitude() {
        return langitude;
    }

    public void setLangitude(float langitude) {
        this.langitude = langitude;
    }

    public float getLattidude() {
        return lattidude;
    }

    public void setLattidude(float lattidude) {
        this.lattidude = lattidude;
    }

    @Override
    public String toString() {
        return "ScheduleInJSON{" +
                "kav=" + kav +
                ", makat=" + makat +
                ", halofa=" + halofa +
                ", direction=" + direction +
                ", distanceFromDistinationToOrigin=" + distanceFromDistinationToOrigin +
                ", start=" + start +
                ", end=" + end +
                ", origin='" + origin + '\'' +
                ", distination='" + distination + '\'' +
                ", langitude=" + langitude +
                ", lattidude=" + lattidude +
                '}';
    }
}
