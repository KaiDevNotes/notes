package kai.dev.unit;


public class BarVoid {

    private FooVoid fooVoid;
    
    public BarVoid(FooVoid fooVoid)
    {
        this.fooVoid = fooVoid;
    }
    
    public void bar(String parameter)
    {
        fooVoid.foo(parameter);
    }
}
