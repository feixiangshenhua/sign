


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

        private String gender;       // 1男2女
        private String nick;    //昵称
        private String introduce;   //自我介绍
        private String head;    //头像
        private String background;   //背景
        private String age;//年限
        private String share;//分享数量
        private String commentnum;//评论数量
        private String commentall;//总评论
        private String type;//类型 "占星/塔罗/周易"
        private String hour_money;//每小时收费金额
        private String longitude;//经度
        private String latitude;//纬度
        private String immediate;//即时咨询 : 1 关闭 2 开启（预留）
        private String balance;//账户余额
        private String income;//累计收入(单位：分)
        private String withdrawals;//累计提现
        private String idname;//身份证姓名
        private String id_status;//实名认证状态（1：是）
        private String distance;//获取用户与其他用户直接距离
        private String soon_money;//即将到账金额
        private Article article;//分享文章
        private String today;//今天时间戳
        private String time;//咨询师7天的预约状态
        private Comment comment;//分享文章
        private String collect;//获取是否收藏

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
        private String praise;//点赞数量
        private String read;//阅读数量
        private String img;//缩略图
        private String digest;//摘要
        private String title;//文章标题
        private String type;//文章类型（40 占星 ，41塔罗，42 周易）
        private String uid;//用户id
        private String id;//文章id

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

        private String id;//评论id
        private String uid;//
        private String content;//评论内容
        private String star;//星星{0-5}
        private String ctime;//评论时间戳
        private String username;//评论用户名字

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
