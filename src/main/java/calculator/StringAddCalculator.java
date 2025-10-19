package calculator;

import java.util.regex.Pattern;

public class StringAddCalculator {
    private static final String DEFAULT_DELIMITER = "[,:]";
    public int add(String input) {
        //빈 문자는 0으로 반환.
        if (input == null || input.isBlank()) {
            return 0;
        }
        input = input.trim();
        // '//' 로 시작  처리
        if (input.startsWith("//")) {
            int index = input.indexOf('\n');
            if (index < 0) {
                throw new IllegalArgumentException("형식이 잘못되었습니다.");
            }
            String delimiter = input.substring(2, index);
            if (delimiter.length() != 1) {
                throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 합니다.");
            }
            String regex = Pattern.quote(delimiter);
            String[] tokens = input.substring(index + 1).split(regex);
            int sum = 0;
            for (String token : tokens) {
                if (token.isBlank()) {
                    throw new IllegalArgumentException("빈 값이 있습니다.");
                }
                if (!token.matches("\\d+")) {
                    throw new IllegalArgumentException("숫자가 아닌 값이 포함되었습니다: " + token);
                }
                int num = Integer.parseInt(token);

                if (num < 0) {
                    throw new IllegalArgumentException("음수는 허용되지 않습니다: " + num);
                }
                sum += num;            }
            return sum;
        }


        String[] tokens = input.split(DEFAULT_DELIMITER); //DEFAULT_DELIMITER 기준으로 배열 저장

        int sum = 0;
        for (String token : tokens) {
            sum += Integer.parseInt(token.trim());
        }
        return sum;    }
}
