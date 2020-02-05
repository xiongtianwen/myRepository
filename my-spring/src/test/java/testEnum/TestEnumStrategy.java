package testEnum;

public enum TestEnumStrategy {
    ENUM_A(new TestEnumA()),ENUM_B(new TestEnumB());
    private TestEnum testEnum;
    TestEnumStrategy(TestEnum testEnum){
        this.testEnum = testEnum;
    }
    public TestEnum getTestEnum(){
        return this.testEnum;
    }
}
