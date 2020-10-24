///////////////////////////
// CLASS FOR SWERVEDRIVE //
///////////////////////////

/*
Note 1:
According to the SwerveDrive instructions:
"This mathematical transformation requires the distance betwen each wheel axle on the length and width."
"Measure these and write them down."
*/


public class SwerveDrive {

    // These two variables are to be assigned to the measurements that are found from Note 1:
    public final double L;
    public final double W;

    public void drive (double x1, double y1, double x2) {
        double r = Math.sqrt((L * L) + (W * W));
        y1 *= -1;

        double a = x1 - x2 * (L / r);
        double b = x1 + x2 * (L / r);
        double c = y1 - x2 * (W / r);
        double d = y1 + x2 * (W / r);

        // a, b, c, and d are used to help compute the speeds for each of the motors
        double backRightSpeed = Math.sqrt((a * a) + (d * d));
        double backLeftSpeed = Math.sqrt((a * a) + (c * c));
        double frontRightSpeed = Math.sqrt((b * b) + (d * d));
        double frontLeftSpeed = Math.sqrt((b * b) + (c * c));

        /*
        "the wheel angles must also be computed for the four wheels."
        "These will be in a range of -1 to 1" (-180 degrees to 180 degrees)
        */
        double backRightAngle = Math.atan2(a,d) / Math.pi;
        double backLeftAngle = Math.atan2(a,c) / Math.pi;
        double frontRightAngle = Math.atan2(b,d) / Math.pi;
        double frontLeftAngle = Math.atan2(b,c) / Math.pi;
    }
}