class MultCalculate implements ICalculate {
    @Override
    public ComplexNumber calculations(ComplexNumber arg, ComplexNumber currentResult) {
        currentResult.i *= arg.i;
        currentResult.r *= arg.r;
        return currentResult;
    }
}
