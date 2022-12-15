package com.dissi.adventofcode;

import jakarta.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log
public class PrintSolutions {

    private Map<SolutionAnnotation, Method> solutions;
    private Map<SolutionAnnotation, Object> initializedObjects;

    @Value("${config.performance.maxLoops:1}")
    private int performanceLoopCount;

    @Value("${config.performance.maxTime:1}")
    private long maxTimePerMethod;

    @Value("${config.year:-1}")
    private int whatYear;

    @Value("${config.day:-1}")
    private int fromDay;


    private void loadMethods() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage("com.dissi.adventofcode")).setScanners(Scanners.MethodsAnnotated));

        solutions = reflections.getMethodsAnnotatedWith(SolutionAnnotation.class)
            .stream()
            .collect(Collectors.toMap(method -> method.getAnnotation(SolutionAnnotation.class), method -> method));

        initializedObjects = solutions.entrySet().stream().collect(Collectors.toMap(Entry::getKey, kvp -> {
            try {
                return kvp.getValue().getDeclaringClass().getDeclaredConstructors()[0].newInstance();
            } catch (Exception e) {
                log.severe("Can not create " + kvp.getValue().getDeclaringClass());
                throw new IllegalArgumentException(e);
            }
        }));
    }

    private void print(SolutionAnnotation details, Method method, Object implementor) {
        try {
            if (performanceLoopCount > 0) {
                Instant start = Instant.now();
                long timeInNano = maxTimePerMethod * 1_000_000;
                long nanosStart = System.nanoTime();
                int actualLoopCount = 0;
                Object result = null;
                for (int i = 0; i < performanceLoopCount; i++) {
                    result = method.invoke(implementor);
                    actualLoopCount++;
                    if (System.nanoTime() - nanosStart > timeInNano) {
                        break;
                    }

                }
                Instant end = Instant.now();
                log.info(
                    String.format("[%s:%s:%s] CalcTime [%s] Times calculated [%s] Time spent [%s] - Answer [%s]",
                        details.year(),
                        leadWithSpaces(String.valueOf(details.day()), 2),
                        details.section(),
                        leadWithSpaces(getTimeFormat(Duration.between(start, end).dividedBy(actualLoopCount)), 10),
                        leadWithSpaces(String.valueOf(actualLoopCount), String.valueOf(performanceLoopCount).length()),
                        leadWithSpaces(getTimeFormat(Duration.between(start, end)), 10),
                        result));

            } else {
                log.info(String.format("[%s] Day [%s] Section [%s] - Answer [%s]",
                    details.year(),
                    details.day(),
                    details.section(),
                    method.invoke(implementor)));
            }

        } catch (IllegalAccessException | InvocationTargetException e) {
            log.warning(String.format("[%s] Day [%s]  Section [%s] - ERROR: [%s]",
                details.year(),
                details.day(),
                details.section(),
                e.getMessage()));
            e.printStackTrace();
        }
    }

    @SuppressWarnings("squid:S3457")
    public static String leadWithSpaces(String value, int paddingLength) {
        return String.format("%1$" + paddingLength + "s", value);
    }

    private String getTimeFormat(Duration duration) {
        long timeInNanos = duration.toNanos();
        DecimalFormat format = new DecimalFormat("###.000");

        if (timeInNanos < 1000) {
            return format.format(timeInNanos) + "ns";
        } else if (timeInNanos < 1000_000) {
            return format.format(timeInNanos / 1000d) + "µs";
        } else if (timeInNanos < 1_000_000_000) {
            return format.format(timeInNanos / 1_000_000d) + "ms";
        } else {
            return format.format(timeInNanos / 1_000_000_000d) + " s";
        }
    }

    @PostConstruct
    private void doPrint() {
        Instant start = Instant.now();
        loadMethods();
        solutions.entrySet().stream()
            .filter(answerable -> answerable.getKey().day() >= fromDay)
            .filter(answerable -> whatYear == -1 || whatYear == answerable.getKey().year())
            .sorted(getComparator())
            .forEach(kvp -> print(kvp.getKey(), kvp.getValue(), initializedObjects.get(kvp.getKey())));

        Instant end = Instant.now();
        log.info(String.format("Finished solution run in [%sms]",
            (Duration.between(start, end).toNanos() / 1_000_000F)));
    }

    private Comparator<Map.Entry<SolutionAnnotation, Method>> getComparator() {
        return Comparator.<Map.Entry<SolutionAnnotation, Method>>comparingInt(kvp -> kvp.getKey().year())
            .thenComparing(kvp -> kvp.getKey().day())
            .thenComparing(kvp -> kvp.getKey().section())
            .reversed();
    }
}
