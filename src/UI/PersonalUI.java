package UI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DB.DB_Conn_Query;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonalUI {
	
	// 스케줄표 아이디를 불러오기 위한 변수
	private static String ID;
	// 등록에 쓰일 변수
	public static String WEEK = "";
	public static String FIX;
	DB_Conn_Query db = new DB_Conn_Query();
	//----------------------------
	
	private JFrame subFrame;
	
	private JLabel titleLabel;
	private JLabel dateLabel;
	private JLabel fixLabel;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;
	private JLabel endLabel;
	private JLabel memoLabel;
	
	private JLabel yearLabel;
	private JLabel monthLabel;
	private JLabel dayLabel;
	
	private JLabel stHourLabel;
	private JLabel stMinuteLabel;
	
	private JLabel edHourLabel;
	private JLabel edMinuteLabel;
	
	private JTextField titleField;
	private JTextField yearField;
	private JComboBox monthBox;
	private JTextField dayField;
	private JCheckBox fixBox;
	private JComboBox stHourBox;
//	private JComboBox stMinuteBox;
	private JComboBox edHourBox;
//	private JComboBox edMinuteBox;
	private JTextArea memoArea;
	private JScrollPane memoScrollPane;
	private JButton delBtn;
	
	public static String[] monthCb = {"01", "02", "03", "04", "05", "06"
			, "07", "08", "09", "10", "11", "12"};
	public static String[] hourCb = {"09", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22"
	};
	protected static String selected;
	private JLabel yoilLabel;
	private JTextField yoilField;
	
	public static void main(String[] args) {
		new PersonalUI(ID);
	}
	
	public PersonalUI(String id) {
		init(id);
		ID=id;
	}
	
	
	private void init(String id) {
//		String[] minuteCb = {"00", "30"};
		
		subFrame = new JFrame();
		
		titleLabel = new JLabel("일정 제목");
		dateLabel = new JLabel("일정 날짜");
		fixLabel = new JLabel("고정");
		startTimeLabel = new JLabel("시작 시간");
		endTimeLabel = new JLabel("종료 시간");
		memoLabel = new JLabel("일정 메모");
		
		yearLabel = new JLabel("년");
		monthLabel = new JLabel("월");
		dayLabel = new JLabel("일");
		stHourLabel = new JLabel("시");
		edHourLabel = new JLabel("시");
//		stMinuteLabel = new JLabel("분");
//		edMinuteLabel = new JLabel("분");
		titleField = new JTextField();
		yearField = new JTextField();
		monthBox = new JComboBox(monthCb);
		dayField = new JTextField();
		fixBox = new JCheckBox();
		fixBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fixBox.isSelected()==true) {	//고정 체크 : 요일 활성화, 날짜 비활성화
					enabled("1");
				}
				else {	//고정 체크 x : 요일 비활성화, 날짜 활성화
					enabled("0");
				}
			}
		});
		stHourBox = new JComboBox(hourCb);
		stHourBox.setModel(new DefaultComboBoxModel(new String[] {"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"}));
		edHourBox = new JComboBox(hourCb);
		delBtn = new JButton("삭제");
		
		
		subFrame.getContentPane().setLayout(null);
		
		int x = 30;
		int y = 20;
		
		titleLabel.setBounds(342,66, 60, 25);
		dateLabel.setBounds(342,106,60,25);
		startTimeLabel.setBounds(342,181,60,25);
		endTimeLabel.setBounds(342,221,60,25);
		memoLabel.setBounds(342,262,60,25);
		
		titleField.setBounds(412, 66, 250, 25);
		yearField.setBounds(412, 106, 50, 25);
		yearLabel.setBounds(462, 106, 60, 25);
		monthBox.setBounds(482, 106, 50, 25);
		monthLabel.setBounds(532, 106, 60,25);
		dayField.setBounds(552, 106, 50, 25);
		dayLabel.setBounds(602, 106, 60, 25);
		fixLabel.setBounds(622, 106, 50, 25);
		fixBox.setBounds(652,106,32,25);
		stHourBox.setBounds(412, 181, 50,25);
		stHourLabel.setBounds(462, 181, 60, 25);
//		stMinuteBox.setBounds(x+140, y+80, 50, 25);
//		stMinuteLabel.setBounds(x+190, y+80, 60, 25);
		edHourBox.setBounds(412, 221, 50,25);
		edHourLabel.setBounds(462, 221, 60, 25);
//		edMinuteBox.setBounds(x+140, y+120, 50, 25);
//		edMinuteLabel.setBounds(x+190, y+120, 60, 25);
		memoScrollPane = new JScrollPane();
		memoScrollPane.setBounds(412, 261, 250, 95);
		delBtn.setBounds(602, 366, 60, 25);
		
		subFrame.getContentPane().add(titleLabel);
		subFrame.getContentPane().add(dateLabel);
		subFrame.getContentPane().add(fixLabel);
		subFrame.getContentPane().add(startTimeLabel);
		subFrame.getContentPane().add(endTimeLabel);
		subFrame.getContentPane().add(memoLabel);
		subFrame.getContentPane().add(yearLabel);
		subFrame.getContentPane().add(monthLabel);
		subFrame.getContentPane().add(dayLabel);
		
		subFrame.getContentPane().add(titleField);
		subFrame.getContentPane().add(yearField);
		subFrame.getContentPane().add(monthBox);
		subFrame.getContentPane().add(dayField);
		subFrame.getContentPane().add(fixBox);
		subFrame.getContentPane().add(stHourBox);
//		subFrame.add(stMinuteBox);
		subFrame.getContentPane().add(stHourLabel);
//		subFrame.add(stMinuteLabel);
		subFrame.getContentPane().add(edHourBox);
		subFrame.getContentPane().add(stHourBox);
		subFrame.getContentPane().add(edHourLabel);
//		subFrame.add(edMinuteBox);
//		subFrame.add(edMinuteLabel);
		subFrame.getContentPane().add(memoScrollPane);
		//		stMinuteBox = new JComboBox(minuteCb);
		//		edMinuteBox = new JComboBox(minuteCb);
		memoArea = new JTextArea();
		memoScrollPane.setViewportView(memoArea);
		subFrame.getContentPane().add(delBtn);
		
		JScrollPane personalScrollPane = new JScrollPane();
		personalScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		personalScrollPane.setBounds(12, 66, 307, 290);
		subFrame.getContentPane().add(personalScrollPane);
		
		
		
		JLabel lblNewLabel = new JLabel("개인 일정 관리");
		lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 26, 181, 30);
		subFrame.getContentPane().add(lblNewLabel);
		
		JButton addBtn = new JButton("등록");
		// 등록 버튼을 눌렀을 시 쿼리에 데이터 저장
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.executeQuery("SELECT COUNT(*) FROM 스케줄");
				
				int SCNUM=0;
				try {
					while(rs.next()) {
						SCNUM = rs.getInt(1);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(SCNUM);
				String SCNAME = titleField.getText();
				
				int year = Integer.parseInt(yearField.getText());
				int month = Integer.parseInt(monthBox.getSelectedItem().toString());
				int day = Integer.parseInt(dayField.getText());
				
				// 날짜 Date로 변환
				String Days = year+"-"+month+"-"+day;
				DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
				LocalDate DATE = LocalDate.parse(Days, sdf);
				
				System.out.println(DATE);
				 
				int START = Integer.parseInt(stHourBox.getSelectedItem().toString());
				int END = Integer.parseInt(edHourBox.getSelectedItem().toString());
				String MEMO = memoArea.getText();
				
				// 요일 구하는 식
				int totalDays = 0;
				totalDays = totalDays + (year)/4;
				if((year-1900)%4==0 && month <3) {
					totalDays = totalDays -1;
				}
				if(month==1) {
						totalDays = totalDays +day;
				}
				if(month==2) {
					totalDays = totalDays +day+31;
				}
				if(month==3) {
					totalDays = totalDays +day+31+28;
				}
				if(month==4) {
					totalDays = totalDays +day+31+28+31;
				}
				if(month==5) {
					totalDays = totalDays +day+31+28+31+30;
				}
				if(month==6) {
					totalDays = totalDays +day+31+28+31+30+31;
				}
				if(month==7) {
					totalDays = totalDays +day+31+28+31+30+31+30;
				}
				if(month==8) {
					totalDays = totalDays +day+31+28+31+30+31+30+31;
				}
				if(month==9) {
					totalDays = totalDays +day+31+28+31+30+31+30+31+31;
				}
				if(month==10) {
					totalDays = totalDays +day+31+28+31+30+31+30+31+31+30;
				}
				if(month==11) {
					totalDays = totalDays +day+31+28+31+30+31+30+31+31+30+31;
				}
				if(month==12) {
					totalDays = totalDays +day+31+28+31+30+31+30+31+31+30+31+30;
				}
				
				int dow = totalDays%7;
				
				if(dow==0) {
					WEEK = "일";
				}
				if(dow==1) {
					WEEK = "월";
				}
				if(dow==2) {
					WEEK = "화";
				}
				if(dow==3) {
					WEEK = "수";
				}
				if(dow==4) {
					WEEK = "목";
				}
				if(dow==5) {
					WEEK = "금";
				}
				if(dow==6) {
					WEEK = "토";
				}
				
				if(SCNAME.length()==0||yearField.getText().isEmpty()||dayField.getText().isEmpty()) {	//일정 제목과 날짜를 입력하지 않았을 때
						JOptionPane.showMessageDialog(null,"일정 제목과 일정 날짜를 입력하세요.");
					}
				else if(START>=END) { //시작시간이 종료시간보다 늦을 경우 경고창
					JOptionPane.showMessageDialog(null,"시작시간을 잘못 입력했습니다.");
				}
					else {
						if(fixBox.isSelected()) {
							FIX = "1";
							DATE = null;
						}
						else {
							FIX = "0";
						}
							
						SCNUM+=1;
						DB_Conn_Query db = new DB_Conn_Query();
						String query = "insert into 스케줄 values("+SCNUM+","+ID+",'"+SCNAME+"','"+WEEK+"',"+START+","+END+",'"+FIX+"','"+DATE+"','"+MEMO+"')";
						System.out.print(query);
						db.executeUpdate(query);
						// Field 초기화
						titleField.setText("");  
						yearField.setText("");
						dayField.setText("");
						memoArea.setText("");
						JOptionPane.showMessageDialog(null,"일정 등록 성공");
					}
				}	
		});
		
		addBtn.setBounds(462, 366, 60, 25);
		subFrame.getContentPane().add(addBtn);
		
		subFrame.setTitle("개인 일정 관리");
		subFrame.setResizable(false);
			
		subFrame.setSize(700,440);
		subFrame.setVisible(true);
		
		JList<String> personalList = new JList<String>();
		
		personalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		String query = "SELECT 스케줄_이름 "
				+ "FROM 스케줄,유저 "
				+ "WHERE 스케줄.유저_아이디=유저.유저_아이디 "
				+ "AND 유저.유저_아이디="+id;
		ResultSet rs = db.executeQuery(query);
		try {
			while(rs.next()) {
				listModel.addElement(rs.getString("스케줄_이름"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		personalList.setModel(listModel);
		
		personalList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					PersonalUI.selected = personalList.getSelectedValue();
					System.out.println("selected :"+selected);
					String sql = "SELECT 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모 "
							+ "FROM 스케줄 "
							+ "WHERE 스케줄_이름 = '"+selected+"' AND 유저_아이디 = "+id;
					ResultSet rs = db.executeQuery(sql);
					try {
						while(rs.next()) {
							titleField.setText(selected);
							if(rs.getString("고정여부").equals("1")) {		//고정 : 날짜 입력 필요 x
								//yearField.setText((d.getYear()).toString());
								enabled("1");
								yoilField.setText(rs.getString("요일"));
							}
							else {	//비고정 : 날짜 입력 필요, 요일 자동 표시
								Date date = rs.getDate("날짜");
								enabled("0");
								SimpleDateFormat y_date = new SimpleDateFormat("yyyy");
								SimpleDateFormat m_date = new SimpleDateFormat("MM");
								SimpleDateFormat d_date = new SimpleDateFormat("dd");
								yearField.setText(y_date.format(date));
								monthBox.setSelectedIndex(Integer.parseInt(m_date.format(date))-1);
								dayField.setText(d_date.format(date));
								
							}
							stHourBox.setSelectedIndex(rs.getInt("시작시간")-9);
							edHourBox.setSelectedIndex(rs.getInt("종료시간")-9);
							memoArea.setText(rs.getString("메모"));
						}
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		JButton modifyBtn = new JButton("수정");
		modifyBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selected == null) 
					JOptionPane.showMessageDialog(null,"수정할 일정을 선택해주세요");
				else {
					
				}
				
			}
		});
		modifyBtn.setBounds(532, 366, 60, 25);
		subFrame.getContentPane().add(modifyBtn);
		
		personalScrollPane.setViewportView(personalList);
		
		yoilLabel = new JLabel("요일");
		yoilLabel.setBounds(342, 146, 60, 25);
		subFrame.getContentPane().add(yoilLabel);
		
		yoilField = new JTextField();
		yoilField.setEnabled(false);
		yoilField.setBounds(412, 146, 50, 25);
		subFrame.getContentPane().add(yoilField);
		
		JButton refreshBtn = new JButton("새로고침");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> listModel = new DefaultListModel<String>();
				
				String query = "SELECT 스케줄_이름 "
						+ "FROM 스케줄,유저 "
						+ "WHERE 스케줄.유저_아이디=유저.유저_아이디 "
						+ "AND 유저.유저_아이디="+id;
				ResultSet rs = db.executeQuery(query);
				try {
					while(rs.next()) {
						listModel.addElement(rs.getString("스케줄_이름"));
					}
				}
				catch (SQLException e2) {
					e2.printStackTrace();
				}
				personalList.setModel(listModel);
				
				titleField.setText("");
				yoilField.setText("");
				yearField.setText("");
				monthBox.setSelectedIndex(0);
				dayField.setText("");
				stHourBox.setSelectedIndex(0);
				edHourBox.setSelectedIndex(0);
				memoArea.setText("");
				fixBox.setSelected(false);
				enabled("0");
			}
		});
		refreshBtn.setBounds(565, 26, 97, 23);
		subFrame.getContentPane().add(refreshBtn);
	}
	public void enabled(String b) {
		Boolean tf=true;
		if(b.equals("0"))tf=false;
		
		fixBox.setSelected(tf);
		yoilField.setEnabled(tf);
		yearField.setEnabled(!tf);
		monthBox.setEnabled(!tf);
		dayField.setEnabled(!tf);
	}
}