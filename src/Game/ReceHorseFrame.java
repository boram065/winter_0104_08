package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ReceHorseFrame extends JFrame{
	JLabel[] horses = new JLabel[5];
	JLabel flag;
	JLabel line;
	HorseThread[] ht = new HorseThread[horses.length];
	int[] winnerIndex = new int[horses.length];
	int index;
	String[] combostr = {"1번 : 윤환쌤", "2번 : 영철쌤", "3번 : 이해원", "4번 : 조서현", "5번 : 황혜경"};
	String[] name = {"플레이어1", "플레이어2"};
	JComboBox<String> combo = new JComboBox<String>(combostr);
	JComboBox<String> combo2 = new JComboBox<String>(combostr);
	int betingIndex; int betingIndex2;
	
	public ReceHorseFrame() {
		JPanel jp = new JPanel(null);
		ImageIcon icon = null;
		ImageIcon fh = new ImageIcon("images/flag.jpg");
		flag = new JLabel(fh);
		flag.setBounds(670, 20, 50, 50);
		ImageIcon lh = new ImageIcon("images/line.jpg");
		line = new JLabel(lh);
		line.setBounds(680, 80, 20, 600);
		jp.setBackground(Color.WHITE);
		jp.add(flag);
		jp.add(line);
		
		JPanel jpN = new JPanel();
		JButton be = new JButton("게임베팅");
		JButton start = new JButton("게임시작");
		be.addActionListener(startL);
		start.addActionListener(startL);
		JLabel name1 = new JLabel(name[0]);
		JLabel name2 = new JLabel(name[1]);
		jpN.add(start);
		jpN.add(be);
		jpN.add(name1);
		jpN.add(combo);
		jpN.add(name2);
		jpN.add(combo2);
		
		for(int i = 0; i < horses.length; i++) {
			icon = new ImageIcon("images/말" + (i+1) + ".jpg");
			horses[i] = new JLabel(icon);
			horses[i].setLocation(0, 100+i*85);
			horses[i].setSize(41, 50);
			jp.add(horses[i]);
		}//for
		add(jp, "Center");
		add(jpN, "North");
		
		setTitle("경주마게임");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(750, 600);
		setLocationRelativeTo(null); //창 가운데 띄우기
		setVisible(true);
		setResizable(false);
		
		for(int i = 0; i < horses.length; i++) {
			System.out.println(winnerIndex);
		}
	}
	
	public static void main(String[] args) {
		new ReceHorseFrame();
	}//main
	
	ActionListener startL = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()){
					case "게임베팅" : 
						betingIndex = combo.getSelectedIndex();
						betingIndex2 = combo2.getSelectedIndex();
						break;
					case "게임시작" : 
						for (int i = 0; i < horses.length; i++) {
							HorseThread t = new HorseThread(horses[i], i);
							t.start();
						}//for
						break;
				}
				
			}
	};
		
	public class HorseThread extends Thread{
		JLabel jlHorse = new JLabel();
		int horseIndex;
		int randomValue;
		int flag;
		
		public HorseThread(JLabel jlHorse, int horseIndex) {
			this.jlHorse = jlHorse;
			this.horseIndex = horseIndex;
		}
		
		public void run() {
			while(true) {
				jlHorse.setLocation(jlHorse.getX()+5, jlHorse.getY());
				if(jlHorse.getX() == 670) {
					winnerIndex[index++] = horseIndex;
					if(horseIndex == horses.length-1) {
						JOptionPane.showMessageDialog(ReceHorseFrame.this, "축하합니다. "+(winnerIndex[0]+1)+"번 말이 우승!!!");
						if(winnerIndex[0] == betingIndex) {
							JOptionPane.showMessageDialog(ReceHorseFrame.this, "축하합니다.\n플레이어1 베팅에 성공하셨습니다.");
						}else if(winnerIndex[0] == betingIndex2){
							JOptionPane.showMessageDialog(ReceHorseFrame.this, "축하합니다.\n플레이어2 베팅에 성공하셨습니다.");
						} else {
							JOptionPane.showMessageDialog(ReceHorseFrame.this, "아쉽습니다.\n베팅에 실패하셨습니다.");
						}//if
						index = 0;
						for(int i = 0; i < horses.length; i++) {
							horses[i].setLocation(0, horses[i].getY());
						}//for
					}//if
					break;
				}//if
				
				try {
					Random random = new Random();
					randomValue = random.nextInt(10);
					sleep(10 * randomValue);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}//while
		}
	}
}//class
