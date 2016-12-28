// Класс примера основан на слайдах 1 - 23 презентации:
// http://www.slideshare.net/nunafig/mockito-12079903?next_slideshow=1



package kai.dev.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BarTest {

    private Foo foo;
    private Bar bar;
    
    @Before
    public void init()
    {
        // Создаем заглушку (Mock или Stub, как кто называет)
        foo = Mockito.mock(Foo.class);
        // Предаем ее в теструемый объект (Unit)
        bar = new Bar(foo);
    }
    
    
    // Проверяем вызывался ли метод foo() c таким же параметром,
    // с каким был вызван метод bar() 
    @Test
    public void methodCallWithParticularParam()
    {
        bar.bar("qwe");
        Mockito.verify(foo).foo("qwe");
    }
    
    
    // Просто проверяем вызывается ли метод foo() при вызове
    // метода bar()
    @Test
    public void methodCall()
    {
        bar.bar(Mockito.anyString());
        Mockito.verify(foo).foo(Mockito.anyString());
    }
    
    
    // Просто проверяем с помощью регулярного выражения вызывается ли метод 
    // foo() с аргументом типа String при вызове метода bar()
    @Test
    public void methodCall2()
    {
        bar.bar("qwe");
        Mockito.verify(foo).foo(Mockito.matches("..."));
    }
    
    
    // Проверяем значение, кот. возвращает метод bar().
    // Т.к. значение возвращаемое bar(), зависит от вызова метода foo() заглушки,
    // то мы определяем поведение foo() с помощью .when().thenReturn()
    @Test
    public void checkMethodReturnValue()
    {
        // Если метод foo() вызывается с аргументом "qwe", то
        // то должен вернуть "asd".
        // Если метод foo() вызывать с аргументом отличным от "qwe", то он 
        // вернет null
        Mockito.when(foo.foo("qwe")).thenReturn("asd");
        // Далее мы вызываем метод bar() с аргуметном "qwe", он соответсвенно
        // вызывает foo() c тем же аргуметом, а foo(), как было задано в 
        // предыдущей строке, в ответ на такой вызов, должен вернуть "asd", что
        // мы и проверяем в методе assertEquals, т.е. в итоге bar() должен вернуть
        Assert.assertEquals("asd", bar.bar("qwe"));
    }
    
    
    // Если мы хотим, чтобы foo() всегда возвращал одно и тоже значения 
    // вне зависимости от преданного ему аргуметнта, то используем 
    // Mockito.anyString()
    @Test
    public void checkMethodReturnValue2()
    {
        Mockito.when(foo.foo(Mockito.anyString())).thenReturn("asd");
        Assert.assertEquals("asd", bar.bar("qwe1"));
        Assert.assertEquals("asd", bar.bar("qwe2"));
    }
}
