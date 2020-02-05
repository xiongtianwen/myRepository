package testEnum;

public class Test {
    public static void main(String[] args) {
        TestEnum testEnum = TestEnumStrategy.ENUM_A.getTestEnum();
        System.out.println(testEnum.getClass());
    }
}
