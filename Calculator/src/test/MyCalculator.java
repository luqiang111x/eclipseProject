package test;



import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyCalculator extends JFrame {

	JPanel JPane1; // 设置一个面板 在center
	JTextField textField; // 设置一个文本域在north
	ActionListener myListener;
	public MyCalculator() {
		setTitle("计算机");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		textField = new JTextField();
		
		textField.setFont(new Font("宋体", Font.PLAIN, 16));
		JPane1 = new JPanel(new GridLayout(4, 5, 3, 3));
		container.add(JPane1, BorderLayout.CENTER);
		container.add(textField, BorderLayout.NORTH);

		String[] titles = { "7", "8", "9", "+", "C",
							"4", "5", "6", "-", "T",
							"1", "2", "3", "*", "D",
							"0", "%", ".","/", "=" 
							};
		
		for (int i = 0; i < titles.length; i++) {
			JButton button = useButton(titles[i]);
			button.setFont(new Font("微软雅黑", Font.PLAIN, 24));
			JPane1.add(button);
		}

	}

	public JButton useButton(String title) {
		JButton button = new JButton(String.valueOf(title));
		if(myListener==null) {
			myListener = new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					String btnInformation = e.getActionCommand();
					char key = btnInformation.charAt(0);
					action(key);
					}
			};
		}
		button.addActionListener(myListener);
		return button;
	}
	String a, b, values;
	Boolean flag =false;
	// flag 为false的时候进行数字长度增加的操作 
	//flag 为true的时候进行数字首次输入的操作
	char op ='n', exit ='0';
	Boolean tab =false;//等於后清空textField
	public void action(char key)
	{
		String text;
		switch(key)
		{
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case '0':
				if(flag) {
					
					textField.setText("");
					textField.setText(String.valueOf(key));
					flag = false;
					
				}else {
					text = textField.getText()+key;
					textField.setText(text);
					text="";
				}
				if(tab) {
					textField.setText("");
					textField.setText(String.valueOf(key));
					tab = false;
				}
				break;
		case '.':
			if("".equals(textField.getText()))
			{
				break;
			}else{
					text = textField.getText();
					int i=0;
					for(; i<text.length(); i++)
					{
						if('.'==text.charAt(i))
						{
							break;
						}
					}
					if(i<text.length())
					break;
					text = textField.getText()+key;
					textField.setText(text);
					text = "";
			}
			break;
		case '+':
			if(!"".equals(textField.getText()))
			{
				if('n' ==op) {
					a = textField.getText();
					op ='+';
					flag = true;
				}else {
					b = textField.getText();
					calculator(a,b);
					a = values;
					textField.setText(values);
					op ='+';
					flag = true;
					
				}
				exit ='1';
			}
			break;
			
		case '-':
			if(!"".equals(textField.getText()))
			{
				if('n'==op)
				{
					a = textField.getText();
					op = '-';
					flag =true;
				}else {
					b =textField.getText();
					calculator(a, b);
					a =values;
					textField.setText(values);
					op = '-';
					flag = true;
				}
				exit ='1';
				
			}
			break;
		case '*':
			if(!"".equals(textField.getText()))
			{
				if('n'==op)
				{
					a = textField.getText();
					op = '*';
					flag =true;
				}else {
					b =textField.getText();
					calculator(a, b);
					a =values;
					textField.setText(values);
					op = '*';
					flag = true;
				}
				exit ='1';
				
			}
			break;
		case '/':
			if(!"".equals(textField.getText()))
			{
				if('n'==op)
				{
					a = textField.getText();
					op = '/';
					flag =true;
				}else {
					b =textField.getText();
					calculator(a, b);
					a =values;
					textField.setText(values);
					op = '/';
					flag = true;
				}
				exit ='1';
				
			}
			break;
		case 'D':
			if(!"".equals(textField.getText()))
			{
				if('n'==op)
				{
					a ="1";
					op = 'D';
					flag =true;
					b =textField.getText();
					calculator(a, b);
					a =values;
					textField.setText(values);

				}
				exit ='1';
				
			}
			break;
		case '%':
			if(!"".equals(textField.getText()))
			{
					op='%';
					a = textField.getText();
					flag = true;
					exit ='1';
			}
		case 'T':
			if(!"".equals(textField.getText()))
			{
				text = textField.getText();
				text = text.substring(0,text.length()-1);
				textField.setText(text);
				text ="";
			}
			break;
		
			
		case 'C':
			if(!"".equals(textField.getText()))
			{
				textField.setText("");
				text = "";
				a = b ="1";
			}
			break;
		case '=':
			if('0' == exit)
			{
				textField.setText("");
				break;
			}
			if ("".equals(textField.getText())) // 排除 7*= 7+= 之类错误
				break;
			b = textField.getText();
			values = calculator(a, b);
			textField.setText(String.valueOf(values));
			tab = true;
			break;
			default:
				;
			
			
			
			
		}
	}
	public String calculator(String a, String b)
	{
		double m1 = Double.parseDouble(a);
		double m2 = Double.parseDouble(b);
		double m =0;
		switch(op) {
		case '+':
			m = m1 + m2;
			exit = '0';
			a = b = null;
			op = 'n';
			break;
		case '-':
			m = m1 - m2;
			exit = '0';
			a = b = null;
			op = 'n';
			break;
		case '*':
			m = m1 * m2;
			exit = '0';
			a = b = null;
			op = 'n';
			break;
		case '/':
			if (m2==0)
				break;
			m = m1 / m2;
			exit = '0';
			a = b = null;
			op = 'n';
			break;
		case '%':
			int v1=0,v2=0;
			v1 =(int)m1;
			v2 = (int)m2;
			m = v1%v2;
			break;
		case 'D':
			m = m1 / m2;
			exit = '0';
			a = b = null;
			op = 'n';
			break;
			default :
				;
		
		}
		values = String.valueOf(m);
		return values;
	}
	public static void main(String[] args) {
		new MyCalculator().setVisible(true);
	}

}
