import lombok.Data;

/**
 * @author maxu
 * @date 2019/5/31
 */
@Data
public class Z {
    private Double Zmax;
    private Double Zmin;

    public void computer(double A ,double B) {
        double[] arr = new double[3];
        int count = 0;
        double a = 1;
        double b = -1;
        double c = (A - B - B * B);
        double d = -(B * B);
        for (double x = -100; x < 100; x++) {
            if (a * x * x * x + b * x * x + c * x + d == 0) {
                arr[count++] = x;
            }
        }
        if (Math.abs(arr[2] - arr[1]) >= 1 && Math.abs(arr[2] - arr[0]) >= 1 &&
                Math.abs(arr[2] - arr[0]) >= 1) {
            this.Zmax = Math.max(Math.max(arr[0], arr[1]), arr[2]);
            this.Zmin = Math.min(Math.min(arr[0], arr[1]), arr[2]);
        }
    }
}
