package dao;

public class ExerciceTest extends Exercice {
	private String toTrans;
    private String trans;
    private String inc1;
    private String inc2;
    

    public ExerciceTest(String toTrans, String trans, String inc1, String inc2) {
        this.toTrans = toTrans;
        this.trans = trans;
        this.inc1 = inc1;
        this.inc2 = inc2;
    }

    public String getToTrans() {
        return toTrans;
    }

    public void setToTrans(String toTrans) {
        this.toTrans = toTrans;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getInc1() {
        return inc1;
    }

    public void setInc1(String inc1) {
        this.inc1 = inc1;
    }

    public String getInc2() {
        return inc2;
    }

    public void setInc2(String inc2) {
        this.inc2 = inc2;
    }
}
