package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    // 빈 문자열 입력
    @Test
    void 빈문자열_입력시_0_반환() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    // 쉼표(,) 구분자
    @Test
    void 쉼표_구분자_사용() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    // 콜론(:) 구분자
    @Test
    void 콜론_구분자_사용() {
        assertSimpleTest(() -> {
            run("1:2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    // 쉼표와 콜론 혼합 사용
    @Test
    void 쉼표_콜론_혼합_구분자_사용() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    //커스텀 구분자 사용
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    //커스텀 구분자 - 실제 개행 입력도 정상 동작
    @Test
    void 커스텀_구분자_실제_개행_입력() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    // 커스텀 구분자 - 한 글자 아님 → 예외
    @Test
    void 커스텀_구분자_두글자_입력시_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;;\\n1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    //잘못된 형식 (\\n 누락)
    @Test
    void 커스텀_형식_잘못된_입력시_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    //비숫자 입력
    @Test
    void 비숫자_입력시_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    //연속 구분자(빈 토큰) 입력
    @Test
    void 연속_구분자_입력시_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    //음수 입력
    @Test
    void 음수_입력시_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    //공백 포함 입력
    @Test
    void 공백포함_입력() {
        assertSimpleTest(() -> {
            run(" 1 , 2 : 3 ");
            assertThat(output()).contains("결과 : 6");
        });
    }

    //큰 숫자 입력
    @Test
    void 큰_숫자_입력() {
        assertSimpleTest(() -> {
            run("1000,2000,3000");
            assertThat(output()).contains("결과 : 6000");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
