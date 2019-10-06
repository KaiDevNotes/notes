package root;

import static net.bytebuddy.matcher.ElementMatchers.named;

import java.lang.instrument.Instrumentation;

import org.joda.time.LocalTime;

import com.google.common.collect.ImmutableList;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;

public class Main
{
    public static void main(String[] args)
    {
        initializeByteBuddyAspects();
        
        System.out.println("Examples:");
        System.out.println();
        System.out.println("[1] ---------------------------------------------");
        System.out.println();
        System.out.println(ImmutableList.of("element-1", "element-2", "element-3"));
        System.out.println();
        System.out.println("[2] ---------------------------------------------");
        System.out.println();
        LocalTime.now().plusHours(5);
        System.out.println();
        System.out.println("---------------------------------------------");
    }

    private static void initializeByteBuddyAspects()
    {
        Instrumentation instrumentation = ByteBuddyAgent.install();
        
        new AgentBuilder.Default()
            .type(named("com.google.common.collect.ImmutableList"))
            .transform(
                (builder, typeDescription, classLoader, module) -> 
                    builder.method(named("construct"))
                           .intercept(Advice.to(ImmutableListBehaviorModificationAspect.class))
            ).installOn(instrumentation);
        
        new AgentBuilder.Default()
        .type(named("org.joda.time.field.PreciseDurationField"))
        .transform(
            (builder, typeDescription, classLoader, module) -> 
                builder.method(named("add"))
                       .intercept(Advice.to(PreciseDurationFieldBehaviorModificationAspect.class))
        ).installOn(instrumentation);
    }
}
