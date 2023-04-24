public class SumCalculateFactory implements ICalculateFactory{

    @Override
    public ICalculate createCalculation() {
        return new SumCalculate();
    }
}
