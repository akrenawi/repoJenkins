package Controllers;

public class LatLong {

    private double Latitude;
    private double Longitude;

    public LatLong(){}

    public LatLong(double lat, double longitude){
        Latitude = lat;
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;

            return (dist);
        }
    }

    public double GetDistance(LatLong latLong){
        final int R = 6371; // Radius of the earth in km
        double dLat = convertDegreeToRad(latLong.getLatitude() - this.getLatitude());
        double dLon = convertDegreeToRad(latLong.getLongitude() - this.getLongitude());

        double a = (Math.sin(dLat/2) * Math.sin(dLat/2))
                +
                (Math.cos(convertDegreeToRad(this.getLatitude())) * Math.cos(convertDegreeToRad(latLong.getLatitude())) *
                        Math.sin(dLon/2) * Math.sin(dLon/2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        return d;
    }

    private double convertDegreeToRad(double deg){
        return deg * (Math.PI / 180);
    }

}
