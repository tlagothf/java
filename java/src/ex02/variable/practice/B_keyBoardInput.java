// 파일의 경로
package ex02.variable.practice;

import java.util.Scanner;

// 접근제한자 class 클래스명 {}
public class B_keyBoardInput {
	
	// 접근제한자 반환타입 메서드명(매개변수타입 매개변수명){}
	public void inputByScanner() {
		Scanner scanner = new Scanner(System.in);
		
		
		/*
		 * scanner.nextLine() : 사용자가 입력한 값을 모두 읽어온다. (문자열)
		 * scanner.next() : 사용자가 입력한 값 중에 공백이 있을 경우 공백 이전까지의 값만 읽어온다.
		 * scanner.nextInt() : 정수를 입력받을 때 사용한다.
		 * scanner.nextDouble() : 실수를 입력받을 때 사용한다.
		 * ... 그 밖에 byte, boolean 등의 기본 자료형을 입력받을 때도 마찬가지로 nextXXX()로 입력받으면 된다. 
		 */ 
		
		// 사용자의 입력을 대기 하고 있다가 엔터키가 입력되면 사용자의 입력 값을 반환
		String input;
		// 메세지출력
		System.out.println("이름을 입력 해주세요");
		// 엔터키가 입력 될 때 까지 대기하다가 엔터키가 입력되면 반환
		input = scanner.next(); // 공백을 기준으로 끊어서 반환
		System.out.println(input);
		
		// 이전에 입력된 '엔터' 를 빼주기위해 scanner.nextLine(); 메소드를 한 번 더 써야한다
		// scanner.nextLine(); 메소드는 모든 값을 반환 하기도 하지만 엔터 를 빼주는 역할도 한다
		scanner.nextLine();
		
		System.out.println("주소를 입력 해주세요");
		input = scanner.nextLine(); // 모두반환
		System.out.println(input);
		System.out.println("나이를 입력 해주세요");
		int age = scanner.nextInt(); // 입력값을 숫자로 반환
		System.out.println("사용자의 입력값 : " + age);
		
	}
	public static void main(String[] args) {
		System.out.println("스캐너 객체를 이용해서 입력을 받아 봅시다");
		
		// inputByScanner 실행
		// 1. 객체 생성
		B_keyBoardInput b = new B_keyBoardInput();
		b.inputByScanner();
	}
}
