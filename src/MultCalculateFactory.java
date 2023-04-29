class MultCalculateFactory implements ICalculateFactory{
    @Override
    public ICalculate createCalculation() {
        return new MultCalculate();
    }
}
