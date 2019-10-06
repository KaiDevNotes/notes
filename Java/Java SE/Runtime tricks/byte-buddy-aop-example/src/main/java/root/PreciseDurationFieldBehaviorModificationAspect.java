package root;

import net.bytebuddy.asm.Advice;

public class PreciseDurationFieldBehaviorModificationAspect
{
    @Advice.OnMethodExit
    private static void logHoursAddition(@Advice.AllArguments Object[] args, @Advice.Return long future)
    {        
        System.out.println(Long.valueOf(String.valueOf(args[0])) + "ms (current time) + " + Integer.valueOf(String.valueOf(args[1])) + " hour(s) = " + future + "ms");
    }
}
