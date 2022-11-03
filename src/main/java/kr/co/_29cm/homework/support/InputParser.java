package kr.co._29cm.homework.support;

import java.util.LinkedList;
import java.util.List;

public class InputParser {

    public static List<List<String>> parse(final List<String> inputs) {
        final List<List<String>> data = new LinkedList<>();

        for (final String input : inputs) {
            final List<String> split = List.of(input.split(","));
            if (split.size() == 4) {
                data.add(split);
            }
            if (split.size() > 4) {
                final int beginIndex = input.indexOf("\"");
                final String substring = input.substring(beginIndex, input.indexOf("\"", beginIndex + 1));
                data.add(List.of(split.get(0), substring, split.get(split.size() - 2), split.get(split.size() - 1)));
            }
        }
        return data;
    }
}
