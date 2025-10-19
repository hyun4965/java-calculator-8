package calculator;

import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.regex.Matcher;

public class StringAddCalculator {
    private static final String DEFAULT_DELIMITER = "[,:]";
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.)\n(.*)");

    public int add(String text) {
        // 입력 값 검증
        if (isNullOrBlank(text)) {
            return 0;
        }
        String[] numbers = splitString(text);

        return sumNumbers(numbers);
    }

    //문자열이 비어있는지 여부
    private boolean isNullOrBlank(String text) {
        return text == null || text.isBlank();
    }

    //구분자
    private String[] splitString(String text) {
        String normalizedText = text.replace("\\n", "\n");
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(normalizedText);

        if (matcher.find()) {
            String customDelimiter = matcher.group(1);
            String numbers = matcher.group(2);
            return numbers.split(Pattern.quote(customDelimiter));
        }
        return text.split(DEFAULT_DELIMITER);
    }

    // 숫자 문자열 배열의 합계를 계산
    private int sumNumbers(String[] numbers) {
        return Arrays.stream(numbers)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToInt(this::parseAndValidate)
                .sum();
    }

    // 문자열 하나를 정수로 변환하고 유효성을 검사
    private int parseAndValidate(String numberStr) {
        try {
            int number = Integer.parseInt(numberStr);
            if (number < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("문자열에 숫자가 아닌 값이 포함되어 있습니다: " + numberStr);
        }
    }
}
