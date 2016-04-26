


/**
 * Created by sunyuxiang on 2015/9/18.
 */
public class SetZoneResBean {

    private int count;
    private int status;
    private Res res;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Res getRes() {
        return res;
    }

    public void setRes(Res res) {
        this.res = res;
    }


    public class Res  {

        private String gender;       // 1��2Ů
        private String nick;    //�ǳ�
        private String introduce;   //���ҽ���
        private String head;    //ͷ��
        private String background;   //����
        private String age;//����
        private String share;//��������
        private String commentnum;//��������
        private String commentall;//������
        private String type;//���� "ռ��/����/����"
        private String hour_money;//ÿСʱ�շѽ��
        private String longitude;//����
        private String latitude;//γ��
        private String immediate;//��ʱ��ѯ : 1 �ر� 2 ������Ԥ����
        private String balance;//�˻����
        private String income;//�ۼ�����(��λ����)
        private String withdrawals;//�ۼ�����
        private String idname;//���֤����
        private String id_status;//ʵ����֤״̬��1���ǣ�
        private String distance;//��ȡ�û��������û�ֱ�Ӿ���
        private String soon_money;//�������˽��
        private Article article;//��������
        private String today;//����ʱ���
        private String time;//��ѯʦ7���ԤԼ״̬
        private Comment comment;//��������
        private String collect;//��ȡ�Ƿ��ղ�

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(String commentnum) {
            this.commentnum = commentnum;
        }

        public String getCommentall() {
            return commentall;
        }

        public void setCommentall(String commentall) {
            this.commentall = commentall;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getHour_money() {
            return hour_money;
        }

        public void setHour_money(String hour_money) {
            this.hour_money = hour_money;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getImmediate() {
            return immediate;
        }

        public void setImmediate(String immediate) {
            this.immediate = immediate;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getWithdrawals() {
            return withdrawals;
        }

        public void setWithdrawals(String withdrawals) {
            this.withdrawals = withdrawals;
        }

        public String getIdname() {
            return idname;
        }

        public void setIdname(String idname) {
            this.idname = idname;
        }

        public String getId_status() {
            return id_status;
        }

        public void setId_status(String id_status) {
            this.id_status = id_status;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getSoon_money() {
            return soon_money;
        }

        public void setSoon_money(String soon_money) {
            this.soon_money = soon_money;
        }

        public Article getArticle() {
            return article;
        }

        public void setArticle(Article article) {
            this.article = article;
        }

        public String getToday() {
            return today;
        }

        public void setToday(String today) {
            this.today = today;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Comment getComment() {
            return comment;
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }

        public String getCollect() {
            return collect;
        }

        public void setCollect(String collect) {
            this.collect = collect;
        }
    }

    public class Article {

        private String ctime;//
        private String praise;//��������
        private String read;//�Ķ�����
        private String img;//����ͼ
        private String digest;//ժҪ
        private String title;//���±���
        private String type;//�������ͣ�40 ռ�� ��41���ޣ�42 ���ף�
        private String uid;//�û�id
        private String id;//����id

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public String getRead() {
            return read;
        }

        public void setRead(String read) {
            this.read = read;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public class Comment  {

        private String id;//����id
        private String uid;//
        private String content;//��������
        private String star;//����{0-5}
        private String ctime;//����ʱ���
        private String username;//�����û�����

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }


}
