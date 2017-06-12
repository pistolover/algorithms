package test;

public class TestXY {
    static double pi = 3.14159265358979324;
    static double a = 6378245.0;
    static double ee = 0.00669342162296594323;
    static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    //BD 坐标：116.364314,39.922616
    //GD 116.35862,39.916196

    
    public static void main(String[] args) {
        WGS84ToBd09(116.35862, 39.916196);
    }
    
    
    // WGS84坐标系转成BD09
    public static String WGS84ToBd09(double gg_lat, double gg_lon) {

        String[] redbstring = transform(gg_lat, gg_lon).split(",");// WGS84
                                                                   // ===>GCj_02

        gg_lon = Double.parseDouble(redbstring[0]);
        gg_lat = Double.parseDouble(redbstring[1]);

        // gcj-02 to bd09
        double bd_lat;
        double bd_lon;
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        bd_lon = z * Math.cos(theta) + 0.0065;
        bd_lat = z * Math.sin(theta) + 0.006;

        System.out.println("BD:" + bd_lat + "," + bd_lon);

        return bd_lon + "," + bd_lat;
    }

    // WGS84 ======> GCj_02
    private static String transform(double wgLat, double wgLon) {
        double mgLat;
        double mgLon;
        String resultString = null;
        if (outOfChina(wgLat, wgLon)) {
            resultString = wgLon + "," + wgLat;
            mgLat = wgLat;
            mgLon = wgLon;
            return resultString;
        }

        double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
        double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
        double radLat = wgLat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        mgLat = wgLat + dLat;
        mgLon = wgLon + dLon;

        return mgLon + "," + mgLat;
    }

    private static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }
}
