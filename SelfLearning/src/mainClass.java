public class mainClass {
	private String[] dayName = {"��", "��", "ȭ", "��", "��", "��", "��"};
	private static DataProcess dataP = new DataProcess(7);
	
	public static void main(String[] args){
		//��ư �����°� �߰�.
		//������
		press(5, 1, 9);
		press(3, 1, 10);
		press(4, 1, 14);
		
		//ȭ����
		press(5, 2, 9);
		press(3, 2, 10);
		press(4, 2, 10);
		press(4, 2, 16);
		
		//������
		press(3, 3, 10);
		press(5, 3, 16);
		
		//�����
		press(3, 4, 10);
		press(5, 4, 10);
		press(4, 4, 10);
		press(4, 4, 10);
		
		//�ݿ���
		press(5, 5, 9);
		press(3, 5, 9);
		
		//�����
		
		//�Ͽ���
		press(3, 0, 12);
		press(5, 0, 14);
		press(6, 0, 18);
		
		
		System.out.println("������ 10��");
		dataP.getPrediction(1, 10);
		
		System.out.println("ȭ���� 10��");
		dataP.getPrediction(2, 10);
		
		System.out.println("�ݿ��� 9��");
		dataP.getPrediction(5, 9);
		
	}
	
	public static void press(int _floorNo, int _day, int _hour){
		dataP.pressed(_floorNo, _day, _hour);
	}
}
