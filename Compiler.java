
/*
 * ��¥: 2016.09.12
 * �й�: 201402351
 * �̸�: ������
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

		// test.ad�� �о test.c ���Ϸ� �����ϱ� ���� Buffer,FileŬ������ �̿��Ѵ�.
		BufferedReader br = new BufferedReader(new FileReader("test.ad"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("test.c"));

		String line;
		// ���ڿ��� token������ �и��ϱ����� Ŭ��������.
		StringTokenizer tokenizer;

		bw.write("#include <stdio.h> \nint main() {");

		// while������ �� �پ� �����鼭 ����Ѵ�.
		while ((line = br.readLine()) != null) {
			// ���� �� �پ� �о��� �� � �������� �������� consoleâ���� Ȯ���Ѵ�.
			System.out.println(line);

			// �߰�ȣ, ���ȣ, �޸��� �������� ����Ѵ�.
			tokenizer = new StringTokenizer(line, "() ,[]");
			while (tokenizer.hasMoreTokens()) {

				String token = tokenizer.nextToken();

				switch (token) {

				// ù ��° ���� def�� �д� �κ�
				case "def":
					String list = tokenizer.nextToken(); // list1
					int size = 0;

					bw.write("\n\tlong " + list + "[] = {");
					while (tokenizer.hasMoreTokens()) {

						bw.write(tokenizer.nextToken());
						size++;
						
						// ���� token�� �ִٸ� ,�� ���ְ� ���ٸ� �����ʴ´�. 
						if (tokenizer.hasMoreTokens()) {
							bw.write(",");
						}
					}

					bw.write("};\n");
					bw.write("\tint _AD_size = " + size + ";\n");
					break;

				// �� ��° ���� reduce�� �д� �κ�
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

				// �� ��° ���� print�� �д� �κ�
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

		// ��� ���� ������� ������ close() �Լ��� �Ἥ ������.
		bw.close();
		br.close();

	}

}
