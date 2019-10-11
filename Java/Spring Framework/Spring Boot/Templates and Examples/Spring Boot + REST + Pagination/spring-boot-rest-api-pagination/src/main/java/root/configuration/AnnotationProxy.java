package root.configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AnnotationProxy implements Annotation, InvocationHandler
{
    private final Class<? extends Annotation> annotationType;
    private final Map<String, Object> values;

    @SuppressWarnings("unchecked")
    public static <T extends Annotation> T of(Class<T> annotationType, Map<String, Object> values)
    {
        Map<String, Object> result = new HashMap<>();
        int matchedValues = 0;
        for (Method method : annotationType.getDeclaredMethods())
        {
            String methodName = method.getName();
            if (values.containsKey(methodName))
            {
                result.put(methodName, values.get(methodName));
                matchedValues++;
            }
            else if (method.getDefaultValue() != null)
            {
                result.put(methodName, method.getDefaultValue());
            }
            else
            {
                throw new IllegalArgumentException(
                        "Failed to instantiate " + annotationType + ". No value provided for " + methodName);
            }
        }
        if (matchedValues != values.size())
        {
            throw new IllegalArgumentException(
                    "Failed to instantiate " + annotationType + ". Unknown values provided.");
        }
        return (T) Proxy.newProxyInstance(annotationType.getClassLoader(), new Class[] {annotationType},
                new AnnotationProxy(annotationType, result));
    }

    @Override
    public Class<? extends Annotation> annotationType()
    {
        return annotationType;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
    {
        if ("annotationType".equals(method.getName()))
        {
            return annotationType();
        }
        return values.getOrDefault(method.getName(), method.getDefaultValue());
    }
}
