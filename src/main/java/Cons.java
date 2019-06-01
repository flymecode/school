import java.util.List;

/**
 * 常量
 * @author tianyu
 * @date 2019/5/31
 */
public class Cons {
    // 温度
    private Double T;
    // 压强
    private Double P;
    // 气体常数
    private Double R;


    /**
     * 参数 a
     */
    public Double a;

    /**
     * 参数 b
     */
    public Double b;


    void setA(List<Component> list) {
        Double a = null;
        int count = list.size();
        for (int i = 0; i < count-1; i++) {
            for (int j = i + 1; j < count; j++) {
                Component componenti = list.get(i);
                Component componentj = list.get(j);
                double temp1 = Math.pow(componenti.getAi() * componentj.getAi(), 0.5);
                double temp2 = componenti.getXi() * componentj.getXi() * (1 - componenti.getTri());
                a += (temp1 * temp2);
            }
        }
        this.a = a;
    }

    void setB(List<Component> list) {
        Double b = null;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Component component = list.get(i);
            b += (component.getXi() * component.getBi());
        }
        this.b = b;
    }

    /**
     * 系数 A
     * @return
     */
    Double getA() {
        return this.a * this.P / (Math.pow(this.R * this.T, 2));
    }

    /**
     * 系数 B
     * @return
     */
    Double getB() {
        return this.a * this.P / (this.R * this.T);
    }

    public void setT(Double t) {
        T = t;
    }

    public void setP(Double p) {
        P = p;
    }

    public void setR(Double r) {
        R = r;
    }


    public Double getT() {
        return T;
    }

    public Double getP() {
        return P;
    }

    public Double getR() {
        return R;
    }


}
