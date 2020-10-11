package cn.itcase.FourOperations;
/*
    处理分数，约分和化真分数
    整数可以看成是分母为1的分数
*/
public class Fraction {
    boolean reduced=false;
    public int molecule;     //分子
    public int denominator;   //分母

    //构造
    public Fraction(int molecule, int denominator) {
        this.molecule = molecule;
        this.denominator = denominator;
        reduction();
    }
    public Fraction(int molecule) {
        this.molecule = molecule;
        denominator = 1;
    }

    //整数
    public int getNum(){
        if(denominator!=1){
            System.out.println(new Throwable().getStackTrace()[0].toString());
        }
        return molecule;
    }

    //约分处理
    public void reduction(){
        int x=gcd(molecule,denominator);   //最大公因数
        if(denominator==0){
            System.out.println(new Throwable().getStackTrace()[0]);
        }
        if(x==0){
            //System.out.println(new Throwable().getStackTrace()[0]);
            return;
        }
        molecule /=x;       //分子除公因数得最简分子
        denominator/=x;     //分母除公因数得最简分母
        if(x>1) {
            this.reduced = true;
        }
    }

    public boolean isFraction(){    //如果是分数就返回1
        return !(molecule %denominator==0);
    }
    //返回分数形式
    @Override
    public String toString() {
        if(isFraction()){      //是分数就返回真分数形式
            if(molecule >denominator){
                return (molecule /denominator)+"’"+ molecule %denominator+"/"+denominator;  //带分数
            }
            return molecule +"/"+denominator;   //真分数
        }
        else return ""+ molecule /denominator;  //整数
    }

    //求最大公因数
    private static int gcd(int a,int b) {
        return (b==0)?a:gcd(b,a%b);
    }


    //加法
    public static Fraction add(Fraction a, Fraction b){
        return new Fraction(a.molecule *b.denominator+b.molecule *a.denominator,b.denominator*a.denominator);
    }
    //减法
    public static Fraction sub(Fraction a, Fraction b){
        return new Fraction(a.molecule *b.denominator-b.molecule *a.denominator,b.denominator*a.denominator);
    }
    //乘法
    public static Fraction mul(Fraction a, Fraction b){
        return new Fraction(a.molecule *b.molecule,b.denominator*a.denominator);
    }
    //除法
    public static Fraction div(Fraction a, Fraction b){
        return new Fraction(a.molecule *b.denominator,b.molecule *a.denominator);
    }
    //带分数的整数部分
    public static Fraction with(Fraction a, Fraction b) {
        return new Fraction(b.denominator*a.getNum()+b.molecule,b.denominator);
    }
}
