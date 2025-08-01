public class Main {
    public static void main(String[] args) {
        var email = "aaa@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa@bbb.cccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa@bbb.ccc.";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa@bb.b.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa@bbb.c_cc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa@bb_b.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "a.a.a@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "a__aa@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = ".aaa@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa.@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "_aa-a.aaa_@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "a@b.c";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "a@b.cc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "@b.cc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "a@bb";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
    }
}