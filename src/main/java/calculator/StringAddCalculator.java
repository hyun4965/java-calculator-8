package calculator;

public class StringAddCalculator {
    public int add(String input) {
        //빈 문자는 0으로 반환.
        if (input == null || input.isBlank()) {
            return 0;
        }
        throw new IllegalArgumentException("아직 지원하지 않는 입력입니다.");
    }
}
