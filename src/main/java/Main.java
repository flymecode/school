import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author tianyu
 * @date 2019/5/29
 */
public class Main {
//        213.9 5.44 8.314
//        2
//        0.845 190.6 4.604 1 1 0.011
//        0.1474 305.4 4.88 1 1 0.099
    static Cons cons = new Cons();
    static Scanner input = null;
    public static void main(String[] args) {
        input = new Scanner(System.in);

        /**----------------------------------输入常量---------------------------------------------*/
        System.out.println("请输入环境参数 T、P、R");
        cons.setT(input.nextDouble());
        cons.setP(input.nextDouble());
        cons.setR(input.nextDouble());
        System.out.println("请输入数据量 n > 1");
        // 输入数据总数
        int count = input.nextInt();
        /**----------------------------------输入每个组分---------------------------------------------*/
        List<Component> list = new ArrayList<>();
        System.out.println("请问输入每条组分的数据 Zfi、Tci、Pci、Pri、Tri、W");
        for (int i = 0; i < count; i++) {
            Component component = new Component(cons);
            component.setZfi(input.nextDouble());
            component.setTci(input.nextDouble());
            component.setPci(input.nextDouble());

            component.setPri(input.nextDouble());
            component.setTri(input.nextDouble());
            component.setW(input.nextDouble());
            list.add(component);
        }

        /**----------------------------------计算每一个组分的 Ki ---------------------------------------------*/
        double kdz = 0, kz = 0;
        for (int i = 0; i < list.size(); i++) {
            Component component = list.get(i);
            kz += (component.getKi() / component.getZfi());
            kdz += (component.getKi() * component.getZfi());
        }

        if (kdz > 1 && kz > 1) {
            System.out.println("请输入e");
            double e = input.nextDouble();
            for (int i = 0; i < list.size(); i++) {
                Boolean flag = true;
                Component component = list.get(i);
                double fe;
                do {
                    // TODO 从第三步骤到第二步骤
                    fe = Fe(list, e);
                    if (Math.abs(fe - 0.0001) != 0) {
                        // 到达步骤2
                        component.setE(e);
                        flag = false;
                    } else {
                        System.out.println("请重新调整 e 的大小");
                        e = input.nextDouble();
                    }
                } while (flag);
            }
        } else {
            System.out.println("结束 END");
            return;
        }

        Z z = new Z();
        cons.setA(list);
        cons.setB(list);
        z.computer(cons.getA(), cons.getB());
        for (Component component : list) {
            component.setZ(z);
        }
        getGi(list);
        getLi(list);
        for (int i = 0; i < list.size(); i++) {
            Component component = list.get(i);
            if(Math.abs(component.getFiG() / component.getFiL() - 1) - 0.000001 != 0) {
                System.out.printf("Xi= %f,Yi =%f,Ki=%f,E = %f",component.getXi(), component.getYi(), component.getKi(), component.getE());
            }
        }

    }


    private static Double Fe(List<Component> list, double e) {
        double re = 0;
        for (int i = 0; i < list.size(); i++) {
            Component component = list.get(i);
            re += ((component.getZfi() * (component.getKi() - 1)) / ((component.getKi() - 1) * e + 1));
        }
        return re;
    }

    private static void getGi(List<Component> list) {
        double temp = 0;
        int count = list.size();
        double Kij = 0;
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                System.out.println("请输入 Kij ");
                Kij = input.nextDouble();
                Component componenti = list.get(i);
                Component componentj = list.get(j);
                temp += componenti.getYi() * Math.pow(componenti.getAi() * componentj.getAi(), 0.5) * (1 - Kij);
            }
        }
        for (int i = 0; i < count; i++) {
            double gi = 0;
            Component component = list.get(i);
            gi = component.getBi() / cons.b * (component.getZ().getZmax() - 1) - Math.log(component.getZ().getZmax() - cons.getB()) - cons.getA() / cons.getB()
                    * (temp * 2 / cons.a - (component.getBi() / cons.b)) * Math.log(1 + cons.getB() / component.getZ().getZmax());
            component.setGi(Math.pow(Math.E, gi));
        }
    }

    private static void getLi(List<Component> list) {
        double temp = 0;
        int count = list.size();
        double Kij = 0;
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                System.out.println("请输入 Kij ");
                Kij = input.nextDouble();
                Component componenti = list.get(i);
                Component componentj = list.get(j);
                temp += componenti.getXi() * Math.pow(componenti.getAi() * componentj.getAi(), 0.5) * (1 - Kij);
            }
        }
        for (int i = 0; i < count; i++) {
            double li = 0;
            Component component = list.get(i);
            li = component.getBi() / cons.b * (component.getZ().getZmin() - 1) - Math.log(component.getZ().getZmin() - cons.getB()) - cons.getA() / cons.getB()
                    * (temp * 2 / cons.a - (component.getBi() / cons.b)) * Math.log(1 + cons.getB() / component.getZ().getZmin());
            component.setLi(Math.pow(Math.E, li));
        }
    }

}
