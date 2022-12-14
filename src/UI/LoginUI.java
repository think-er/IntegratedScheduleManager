package UI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Control.LoginSystem;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DB.DB_Conn_Query;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DropMode;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Image;
public class LoginUI extends JFrame {
	
	public JPanel loginPanel;
	private JLabel loginTitleLabel; // 프로그램 제목
	private JLabel loginImgLabel; // 프로그램 아이콘 이미지
	private JLabel loginIdLabel;
	private JLabel loginPwLabel;
	private JTextField loginIdField;
	private JPasswordField loginPwField;
	private JButton loginBtn;
	private JButton toRegisterBtn;
	
	public final static int LOGIN_FRAME_X = 0;
	public final static int LOGIN_FRAME_Y = 0;
	public final static int LOGIN_FRAME_WIDTH = 400;
	public final static int LOGIN_FRAME_HEIGHT = 400;
	
	public final static int LOGIN_LABEL_X = 25;
	public final static int LOGIN_LABEL_Y = 230;
	public final static int LOGIN_LABEL_WIDTH = 60;
	public final static int LOGIN_LABEL_HEIGHT = 30;
	
	public final static int LOGIN_FIELD_X = 80;
	public final static int LOGIN_FIELD_Y = 230;
	public final static int LOGIN_FIELD_WIDTH = 200;
	public final static int LOGIN_FIELD_HEIGHT = 30;
	
	public final static int LOGIN_BTN_X = 290;
	public final static int LOGIN_BTN_Y = 270;
	public final static int LOGIN_BTN_WIDTH = 90;
	public final static int LOGIN_BTN_HEIGHT = 30;
	
	public final static int TO_REGISTER_BTN_X = 135;
	public final static int TO_REGISTER_BTN_Y = 320;
	public final static int TO_REGISTER_BTN_WIDTH = 100;
	public final static int TO_REGISTER_BTN_HEIGHT = 30;
	
	public final static int BACK_BTN_X = 0;
	public final static int BACK_BTN_Y = 0;
	public final static int BACK_BTN_WIDTH = 60;
	public final static int BACK_BTN_HEIGHT = 30;
	private JLabel titleLabel;
	
	public LoginUI() {
		init();
	}
	
	private void init() {
		loginPanel = new JPanel();
		loginPanel.setBounds(LOGIN_FRAME_X, LOGIN_FRAME_Y, 
				LOGIN_FRAME_WIDTH, LOGIN_FRAME_HEIGHT);
		loginPanel.setLayout(null);
		
		loginPanel.setBackground(Color.WHITE);
		
		ImageIcon loginIcon = new ImageIcon("img/login.png");
		Image loginImg = loginIcon.getImage();
		Image changeImg = loginImg.getScaledInstance(150,90, Image.SCALE_AREA_AVERAGING);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel la = new JLabel(changeIcon);
		la.setBounds(100,25,200,100);
		loginPanel.add(la);
		
//		loginTitleLabel = new JLabel("통합 일정 관리");
//		loginTitleLabel.setBounds(150, 10, 100, 30);
//		loginPanel.add(loginTitleLabel);
		
		loginIdLabel = new JLabel("학번");
		loginIdLabel.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		loginIdLabel.setBounds(26, 186,
				LOGIN_LABEL_WIDTH, LOGIN_LABEL_HEIGHT);
		loginPanel.add(loginIdLabel);
		
		
		loginIdField = new JTextField();
		loginIdField.setBackground(new Color(240, 240, 240));
		loginIdField.setForeground(new Color(0, 0, 0));
		loginIdField.setBounds(92, 186, 
				234, 30);
		loginPanel.add(loginIdField);
		
		loginPwLabel = new JLabel("비밀번호");
		loginPwLabel.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		loginPwLabel.setBounds(26, 226, 
				LOGIN_LABEL_WIDTH, LOGIN_LABEL_HEIGHT);
		loginPanel.add(loginPwLabel);
		
		loginPwField = new JPasswordField();
		loginPwField.setBackground(new Color(240, 240, 240));
		loginPwField.setBounds(92, 226,
				234, 30);
		loginPanel.add(loginPwField);
				
		loginBtn = new JButton("로그인");
		loginBtn.setFont(new Font("나눔고딕", Font.BOLD, 13));
		
		loginBtn.addActionListener(new ActionListener() {
			
		//----------------------로그인 기능 추가------------------------------
			public void actionPerformed(ActionEvent e) {
				String id = loginIdField.getText();
				String pw = String.valueOf(loginPwField.getPassword());
				
				LoginSystem login = new LoginSystem();
				String m = login.LoginSystem(id, pw);
				
				if(m==null) {
					JOptionPane.showMessageDialog(null,"로그인실패.");
				}
				else {
					JOptionPane.showMessageDialog(null,"로그인되었습니다.");
					loginPanel.setVisible(false);
					HomeUI homePanel = new HomeUI(id,m);
					homePanel.homePanel.setVisible(true);
				}
			}
		});
		//-----------------------------------------------------------------
		loginBtn.setBounds(215, 276, 
				111, 30);
		loginPanel.add(loginBtn);
		
		toRegisterBtn = new JButton("회원가입");
		toRegisterBtn.setFont(new Font("나눔고딕", Font.BOLD, 13));
		
		toRegisterBtn.setBounds(92, 276, 
				111, 30);
		toRegisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUI registerPanel = new RegisterUI();
				loginPanel.setVisible(false);
				registerPanel.registerPanel.setVisible(true);
		}
	});
		loginPanel.add(toRegisterBtn);
		
		MainFrame.frame.getContentPane().add(loginPanel);
		
		titleLabel = new JLabel("<html><body><center>통합 일정 관리 프로그램</center></body></html>", JLabel.CENTER);
		titleLabel.setFont(new Font("나눔고딕", Font.PLAIN, 20));
		titleLabel.setBounds(92, 120, 234, 56);
		loginPanel.add(titleLabel);
		
		//------------------------------------------ 팀 만들기 버튼
		
		JButton createTeamBtn = new JButton("팀 만들기");
		createTeamBtn.setFont(new Font("나눔고딕", Font.BOLD, 12));
		createTeamBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminAuthUI();
			}
		});
		
		createTeamBtn.setBounds(274, 328, 97, 23);
		loginPanel.add(createTeamBtn);
		MainFrame.frame.setTitle("로그인");
		MainFrame.frame.setSize(LOGIN_FRAME_WIDTH, LOGIN_FRAME_WIDTH);
		MainFrame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.frame.setResizable(false);
	}
}