// Класс примера основан на слайдах 23 -  презентации:
// http://www.slideshare.net/nunafig/mockito-12079903?next_slideshow=1



package kai.dev.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BarVoidTest {

    private FooVoid fooVoid;
    private BarVoid barVoid;
    
    @Before
    public void init()
    {
        // Создаем заглушку (Mock или Stub, как кто называет)
        fooVoid = Mockito.mock(FooVoid.class);
        // Предаем ее в теструемый объект (Unit)
        barVoid = new BarVoid(fooVoid);
    }
    
    
    // Проверяем вызывался ли метод foo() (кот. ничего не возвращает)
    // c таким же параметром, с каким был вызван метод bar() 
    @Test
    public void methodCallWithParticularParam()
    {
        Mockito.doNothing().when(fooVoid).foo("qwe");        
        barVoid.bar("qwe");
        Mockito.verify(fooVoid).foo("qwe");
    }
}
