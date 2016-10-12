
/*
 * 날짜: 2016.09.12
 * 학번: 201402351
 * 이름: 박은식
 * OS: Window
 * Compiler: Eclipse
 * 
 * */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Compiler {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// test.ad를 읽어서 test.c 파일로 추출하기 위해 Buffer,File클래스를 이용한다.
		BufferedReader br = new BufferedReader(new FileReader("test.ad"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("test.c"));

		String line;
		// 문자열을 token단위로 분리하기위한 클래스변수.
		StringTokenizer tokenizer;

		bw.write("#include <stdio.h> \nint main() {");

		// while문으로 한 줄씩 읽으면서 출력한다.
		while ((line = br.readLine()) != null) {
			// 먼저 한 줄씩 읽었을 때 어떤 형식으로 나오는지 console창에서 확인한다.
			System.out.println(line);

			// 중괄호, 대괄호, 콤마를 구분지어 출력한다.
			tokenizer = new StringTokenizer(line, "() ,[]");
			while (tokenizer.hasMoreTokens()) {

				String token = tokenizer.nextToken();

				switch (token) {

				// 첫 번째 줄인 def를 읽는 부분
				case "def":
					String list = tokenizer.nextToken(); // list1
					int size = 0;

					bw.write("\n\tlong " + list + "[] = {");
					while (tokenizer.hasMoreTokens()) {

						bw.write(tokenizer.nextToken());
						size++;
						
						// 다음 token이 있다면 ,를 써주고 없다면 쓰지않는다. 
						if (tokenizer.hasMoreTokens()) {
							bw.write(",");
						}
					}

					bw.write("};\n");
					bw.write("\tint _AD_size = " + size + ";\n");
					break;

				// 두 번째 줄인 reduce를 읽는 부분
				case "reduce":

					String list1 = tokenizer.nextToken();
					String operater = tokenizer.nextToken();
					String initializer = tokenizer.nextToken();
					String reduce_result = tokenizer.nextToken();
					String _AD_i = "_AD_i";

					bw.write("\n\tlong " + reduce_result + " = " + initializer + ";\n");
					bw.write("\tint " + _AD_i + " = 0;\n\n");
					bw.write("\tfor( " + _AD_i + " = 0; " + _AD_i + " < _AD_size " + "; " + _AD_i + "++){\n");
					bw.write("\t\t" + reduce_result + " " + operater + "= " + list1 + "[" + _AD_i + "];\n");

					bw.write("\t}\n");
					break;

				// 세 번째 줄인 print를 읽는 부분
				case "print":
					String print_result = tokenizer.nextToken();
					bw.write("\n\tprintf(\"%d\\n\"" + ", " + print_result + ");\n");
					bw.write("\n\treturn 0;");
					bw.write("\n}");
					break;
				default:
					break;
				}
			}
		}

		// 모든 파일 입출력이 끝나면 close() 함수를 써서 끝낸다.
		bw.close();
		br.close();

	}

}
