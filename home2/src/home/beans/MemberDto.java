package home.beans;

public class MemberDto {
	private int no;						//ȸ����ȣ
	private String email;			//�̸���
	private String pw;				//��й�ȣ
	private String name;			//�̸�
	private String birth;			//�������
	private String phone;			//��ȭ��ȣ
	private String question;		//Ȯ������
	private String answer;		//Ȯ�δ亯
	private String regist;			//�����Ͻ�
	private String auth;				//����
	private String recent;			//���� �����Ͻ�
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getRegist() {
		return regist;
	}
	public void setRegist(String regist) {
		this.regist = regist;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getRecent() {
		return recent;
	}
	public void setRecent(String recent) {
		this.recent = recent;
	}
	@Override
	public String toString() {
		return "MemberDto [no=" + no + ", email=" + email + ", pw=" + pw + ", name=" + name + ", birth=" + birth
				+ ", phone=" + phone + ", question=" + question + ", answer=" + answer + ", regist=" + regist
				+ ", auth=" + auth + ", recent=" + recent + "]";
	}
	public MemberDto() {
		super();
	}
}