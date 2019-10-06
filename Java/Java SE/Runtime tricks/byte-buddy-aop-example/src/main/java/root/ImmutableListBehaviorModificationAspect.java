package root;

import net.bytebuddy.asm.Advice;

public class ImmutableListBehaviorModificationAspect
{
    private static final String REPLACEMENT = "first-element-in-list";
    
    @Advice.OnMethodEnter
    private static void modifyElementsDuringConstruction(@Advice.Argument(0) Object[] elements)
    {
        replaceFistElement(elements);
    }

    /*
     * This method should be public, otherwise we will get java.lang.IllegalAccessError: 
     * class com.google.common.collect.ImmutableList tried to access private method
     * root.ImmutableListBehaviorModificationAspect.replaceFistElement
     */
    public static void replaceFistElement(Object[] elements)
    {
        if (elements.length == 0) return;
        
        System.out.println("First element [" + elements[0] + "] will be replaced by [" + REPLACEMENT + "]:\n");
        elements[0] = REPLACEMENT;
    }
}
