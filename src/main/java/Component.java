/**
 * 组分
 *
 * @author tianyu
 * @date 2019/5/31
 */
public class Component {
    // 临界温度
    private Double Tci;
    //
    private Double Tri;
    // 临界压力
    private Double Pci;
    //
    private Double Pri;
    // 组成
    private Double Zfi;
    // 偏心因子
    private Double w;

    private Double Ki;

    private Double e;

    private Cons cons;

    private Double gi;

    private Double li;

    private Z z;

    public Z getZ() {
        return z;
    }

    public void setZ(Z z) {
        this.z = z;
    }

    public Double getE() {
        return this.e;
    }

    public Component(Cons cons) {
        this.cons = cons;
    }

    public Double getKi() {
        return this.Pci / cons.getP() * Math.exp(5.42 * (1 - this.Tci / cons.getT()));
    }

    public Double getXi() {
        return this.Zfi / (this.getE() * (this.getKi() - 1) + 1);
    }

    public Double getYi() {
        return this.Ki * getXi();
    }

    public Double getMi() {
        return 0.480 + 1.574 * this.w - 0.176 * this.w * this.w;
    }

    public Double getAi() {
        double a = (0.42747 * cons.getR() * cons.getR() * this.Tci * this.Tci) / this.Pci;
        double b = Math.pow(1 + this.getMi() * (1 - this.Tri), 2);
        return a / b;
    }

    public Double getBi() {
        return 0.08664 * cons.getR() * this.Tci / this.Pci;
    }

    public Double getFiG() {
        return getGi() * getYi() * cons.getP();
    }

    public Double getFiL() {
        return getFiG();
    }

    public Double getGi() {
        return gi;
    }

    public void setGi(Double gi) {
        this.gi = gi;
    }

    public Double getLi() {
        return li;
    }

    public void setLi(Double li) {
        this.li = li;
    }

    public Double getTci() {
        return Tci;
    }

    public Double getTri() {
        return Tri;
    }

    public Double getPci() {
        return Pci;
    }

    public Double getPri() {
        return Pri;
    }

    public Double getZfi() {
        return Zfi;
    }


    public void setTci(Double tci) {
        Tci = tci;
    }

    public void setTri(Double tri) {
        Tri = tri;
    }

    public void setPci(Double pci) {
        Pci = pci;
    }

    public void setPri(Double pri) {
        Pri = pri;
    }

    public void setZfi(Double zfi) {
        Zfi = zfi;
    }

    public void setW(Double w) {
        this.w = w;
    }

    public void setE(Double e) {
        this.e = e;
    }

}
